package com.epam.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.epam.dto.Credentials;
import com.epam.dto.EmailDto;
import com.epam.dto.TraineeDto;
import com.epam.dto.request.TrainerRegistrationRequest;
import com.epam.dto.request.UpdateTrainerRequest;
import com.epam.dto.response.TrainerProfileResponse;
import com.epam.dto.response.UpdatedTrainerResponse;
import com.epam.exceptions.TrainerException;
import com.epam.exceptions.UserException;
import com.epam.model.Trainer;
import com.epam.model.TrainingType;
import com.epam.model.User;
import com.epam.repository.TrainerRepository;
import com.epam.repository.TrainingTypeRepository;
import com.epam.repository.UserRepository;
import com.epam.service.interfaces.TrainerService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class TrainerServiceImpl implements TrainerService {

	private final UserRepository userRepository;
	private final TrainerRepository trainerRepository;
	private final TrainingTypeRepository trainingTypeRepository;
	private final KafkaTemplate<String, EmailDto> emailKafkaService;
	private final PasswordEncoder passwordEncoder;
	@Value("${send.ccemail}")
	private String ccEmail;
	@Value("${kafka.message.topic.name}")
	private String kafkaEmailTopicName;
	@Value("${default.password}")
	private String password;

	@Override
	public TrainerProfileResponse getTrainerProfile(String username) {
		log.info("TrainerServiceImpl : getTrainerProfile ");
		Trainer trainer = trainerRepository.findByUserUsername(username)
				.orElseThrow(() -> new TrainerException("Trainee did not found with this username " + username));
		List<TraineeDto> listOfTraineesDto = trainer.getListOfTrainees().stream()
				.map(trainees -> TraineeDto.builder().username(trainees.getUser().getUsername())
						.firstname(trainees.getUser().getFirstname()).lastname(trainees.getUser().getLastname())
						.build())
				.toList();
		return new TrainerProfileResponse(trainer.getUser().getFirstname(), trainer.getUser().getLastname(),
				trainer.getTrainingType().getTrainingTypeName(), trainer.getUser().isActive(), listOfTraineesDto);
	}
	

	@Override 
	public Credentials trainerRegistration(TrainerRegistrationRequest trainerRegistrationRequest) {
		log.info("TrainerServiceImpl : TrainerRegistration ");
		String username = trainerRegistrationRequest.getEmail()
				.subSequence(0, trainerRegistrationRequest.getEmail().indexOf('@')).toString();
		emailKafkaService.send(kafkaEmailTopicName, EmailDto.builder().toEmail(trainerRegistrationRequest.getEmail())
				.ccEmail(ccEmail).subject("Trainer Registration Succesful").remarks("Have to reset Password")
				.body("Hi" + trainerRegistrationRequest.getFirstname() + ",\nSuccesfully Registered..").build());
		User user = userRepository.save(User.builder().firstname(trainerRegistrationRequest.getFirstname())
				.lastname(trainerRegistrationRequest.getLastname()).email(trainerRegistrationRequest.getEmail())
				.username(username).password(passwordEncoder.encode(password)).build());
		TrainingType trainingType = trainingTypeRepository
				.findByTrainingTypeNameIgnoreCase(trainerRegistrationRequest.getSpecialization());
		trainerRepository.save(Trainer.builder().trainingType(trainingType).user(user).build());
		return Credentials.builder().username(username).password(password).build();
	}

	@Override
	public UpdatedTrainerResponse updateTrainerProfile(UpdateTrainerRequest updateTrainerRequest) {
		log.info("TrainerServiceImpl : updateTrainerProfile ");
		User user = userRepository.findByUsername(updateTrainerRequest.getUsername())
				.orElseThrow(() -> new UserException("User Not Found.."));
		user.setActive(updateTrainerRequest.isActive());
		user.setFirstname(updateTrainerRequest.getFirstname());
		user.setLastname(updateTrainerRequest.getLastname());
		user = userRepository.save(user);
		Trainer trainer = trainerRepository.findByUserUsername(user.getUsername())
				.orElseThrow(() -> new TrainerException("User not found.."));
		TrainingType trainingType = trainingTypeRepository
				.findByTrainingTypeNameIgnoreCase(updateTrainerRequest.getSpecialization());
		trainer.setTrainingType(trainingType);
		trainer = trainerRepository.save(trainer);
		List<TraineeDto> listOfTraineesDto = trainer.getListOfTrainees().stream()
				.map(trainees -> TraineeDto.builder().username(trainees.getUser().getUsername())
						.firstname(trainees.getUser().getFirstname()).lastname(trainees.getUser().getLastname())
						.build())
				.toList();
		emailKafkaService.send(kafkaEmailTopicName,
				EmailDto.builder().toEmail(updateTrainerRequest.getUsername() + "@gmail.com").ccEmail(ccEmail)
						.subject("Trainer Updation Succesful").remarks("Have to reset Password")
						.body("Hi " + updateTrainerRequest.getFirstname() + ",\n Updated Succesfully..").build());
		return new UpdatedTrainerResponse(user.getUsername(), user.getFirstname(), user.getLastname(),
				trainer.getTrainingType().getTrainingTypeName(), user.isActive(), listOfTraineesDto);
	}

}
