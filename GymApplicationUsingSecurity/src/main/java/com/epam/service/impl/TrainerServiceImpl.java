package com.epam.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.epam.dto.Credentials;
import com.epam.dto.EmailDto;
import com.epam.dto.TraineeDto;
import com.epam.dto.request.TrainerRegistrationRequest;
import com.epam.dto.request.UpdateTrainerRequest;
import com.epam.dto.response.TrainerProfileResponse;
import com.epam.dto.response.UpdatedTrainerResponse;
import com.epam.exceptions.TraineeException;
import com.epam.exceptions.TrainerException;
import com.epam.exceptions.UserException;
import com.epam.model.Trainer;
import com.epam.model.TrainingType;
import com.epam.model.User;
import com.epam.repository.TrainerRepository;
import com.epam.repository.TrainingTypeRepository;
import com.epam.repository.UserRepository;
import com.epam.service.interfaces.TrainerService;
import com.epam.utilities.RandomPasswordGenerator;

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
	private String ccEmail="supriyadivvela8187@gmail.com";
	private String kafkaEmailTopicName="email-messages";

	@Override
	public TrainerProfileResponse getTrainerProfile(String username) {
		log.info("TrainerServiceImpl : getTrainerProfile ");
		Trainer trainer = trainerRepository.findByUserUsername(username)
				.orElseThrow(() -> new TraineeException("Trainee did not found with this username " + username));
		List<TraineeDto> listOfTraineesDto = trainer.getListOfTrainees().stream()
				.map(trainees -> TraineeDto.builder().username(trainees.getUser().getUsername())
						.firstname(trainees.getUser().getFirstname()).lastname(trainees.getUser().getLastname())
						.build())
				.collect(Collectors.toList());
		return new TrainerProfileResponse(trainer.getUser().getFirstname(), trainer.getUser().getLastname(),
				trainer.getTrainingType().getTrainingTypeName(), trainer.getUser().isActive(), listOfTraineesDto);
	}

	@Override
	public Credentials trainerRegistration(TrainerRegistrationRequest trainerRegistrationRequest) {
		log.info("TrainerServiceImpl : TrainerRegistration ");
		String username = trainerRegistrationRequest.getEmail()
				.subSequence(0, trainerRegistrationRequest.getEmail().indexOf('@')).toString();
		String password = RandomPasswordGenerator.generateRandomPassword();
		log.info(kafkaEmailTopicName);;
		emailKafkaService.send(kafkaEmailTopicName,EmailDto.builder().toEmail(trainerRegistrationRequest.getEmail())
				.ccEmail(ccEmail).subject("Trainer Registration Succesful")
				.remarks("Have to reset Password")
				.body("Hi" + trainerRegistrationRequest.getFirstname() + ",\nSuccesfully Registered..").build());
		User user = userRepository.save(User.builder().firstname(trainerRegistrationRequest.getFirstname())
				.lastname(trainerRegistrationRequest.getLastname()).email(trainerRegistrationRequest.getEmail())
				.username(username).password(password).build());
		TrainingType trainingType = trainingTypeRepository
				.findByTrainingTypeNameIgnoreCase(trainerRegistrationRequest.getSpecialization())
				.orElseGet(() -> trainingTypeRepository.save(TrainingType.builder()
						.trainingTypeName(trainerRegistrationRequest.getSpecialization()).build()));
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
				.findByTrainingTypeNameIgnoreCase(updateTrainerRequest.getSpecialization()).orElseGet(
						() -> trainingTypeRepository.save(new TrainingType(updateTrainerRequest.getSpecialization())));
		trainer.setTrainingType(trainingType);
		trainer = trainerRepository.save(trainer);
		List<TraineeDto> listOfTraineesDto = trainer.getListOfTrainees().stream()
				.map(trainees -> TraineeDto.builder().username(trainees.getUser().getUsername())
						.firstname(trainees.getUser().getFirstname()).lastname(trainees.getUser().getLastname())
						.build())
				.collect(Collectors.toList());;
		emailKafkaService.send(kafkaEmailTopicName,EmailDto.builder().toEmail(updateTrainerRequest.getUsername() + "@gmail.com")
				.ccEmail(ccEmail).subject("Trainer Updation Succesful")
				.remarks("Have to reset Password")
				.body("Hi " + updateTrainerRequest.getFirstname() + ",\n Updated Succesfully..").build());
		return new UpdatedTrainerResponse(user.getUsername(), user.getFirstname(), user.getLastname(),
				trainer.getTrainingType().getTrainingTypeName(), user.isActive(), listOfTraineesDto);
	}

}
