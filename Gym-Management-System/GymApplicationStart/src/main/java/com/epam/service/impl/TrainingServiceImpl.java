package com.epam.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.epam.dto.TrainingDto;
import com.epam.dto.TrainingSummaryDto;
import com.epam.dto.response.TraineeTrainingResponse;
import com.epam.dto.response.TrainerTrainingResponse;
import com.epam.exceptions.TraineeException;
import com.epam.exceptions.TrainerException;
import com.epam.model.Trainee;
import com.epam.model.Trainer;
import com.epam.model.Training;
import com.epam.repository.TraineeRepository;
import com.epam.repository.TrainerRepository;
import com.epam.repository.TrainingRepository;
import com.epam.service.interfaces.TrainingService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@Service
public class TrainingServiceImpl implements TrainingService {

	private TrainingRepository trainingRepository;
	private TraineeRepository traineeRepository;
	private TrainerRepository trainerRepository;
	private KafkaTemplate<String, TrainingSummaryDto> kafkaReportsService;

	@Override
	public void addTraining(TrainingDto trainingDto) {
		log.info("TrainingServiceImpl : addTraining ");
		Trainee trainee = traineeRepository.findByUserUsername(trainingDto.getTraineeUsername())
				.orElseThrow(() -> new TraineeException("Trainee does not exits.."));
		Trainer trainer = trainerRepository
				.findByUserUsernameAndTrainingTypeTrainingTypeNameIgnoreCase(trainingDto.getTrainerUsername(),
						trainingDto.getTrainingType())
				.orElseThrow(() -> new TrainerException(trainingDto.getTrainerUsername() + " doesnot teach "
						+ trainingDto.getTrainingType() + " or trainer doesnot exist.."));
		Training training = trainingRepository.save(Training.builder().trainee(trainee).trainer(trainer).duration(trainingDto.getTrainingDuration())
				.trainingDate(trainingDto.getTrainingDate()).trainingName(trainingDto.getTrainingName()).build());
		trainer.getListOfTrainees().add(trainee);
		trainerRepository.save(trainer);
		kafkaReportsService.send("report-messages",
				TrainingSummaryDto.builder().trainerUsername(trainer.getUser().getUsername())
						.trainerFirstname(trainer.getUser().getFirstname())
						.trainerLastname(trainer.getUser().getLastname()).trainerstatus(trainer.getUser().isActive())
						.date(training.getTrainingDate()).build());

	}

	@Override
	public List<TraineeTrainingResponse> getTraineesTrainingList(String username, LocalDate periodFrom,
			LocalDate periodTo, String trainerName, String trainingType) {
		log.info("TrainingServiceImpl : getTraineesTrainingList ");
		return trainingRepository.getTraineeTrainingsList(username, periodFrom, periodTo, trainerName, trainingType)
				.stream()
				.map(training -> TraineeTrainingResponse.builder().trainingName(training.getTrainingName())
						.trainerUsername(training.getTrainer().getUser().getUsername())
						.trainingDate(training.getTrainingDate())
						.trainingDuration(training.getDuration()).build())
				.toList();
	}

	@Override
	public List<TrainerTrainingResponse> getTrainersTrainingList(String username, LocalDate periodFrom,
			LocalDate periodTo, String traineeName) {
		log.info("TrainingServiceImpl : getTrainersTrainingList ");
		return trainingRepository.getTrainerTrainingsList(username, periodFrom, periodTo, traineeName).stream()
				.map(training -> TrainerTrainingResponse.builder().trainingName(training.getTrainingName())
						.traineeUsername(training.getTrainee().getUser().getUsername())
						.trainingDate(training.getTrainingDate())
						.trainingDuration(training.getDuration()).build())
				.toList();

	}
}
