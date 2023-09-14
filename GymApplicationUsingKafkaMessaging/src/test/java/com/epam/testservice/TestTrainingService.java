package com.epam.testservice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;

import com.epam.dto.TrainingDto;
import com.epam.dto.TrainingSummaryDto;
import com.epam.dto.response.TraineeTrainingResponse;
import com.epam.dto.response.TrainerTrainingResponse;
import com.epam.exceptions.TraineeException;
import com.epam.exceptions.TrainerException;
import com.epam.model.Trainee;
import com.epam.model.Trainer;
import com.epam.model.Training;
import com.epam.model.TrainingType;
import com.epam.model.User;
import com.epam.repository.TraineeRepository;
import com.epam.repository.TrainerRepository;
import com.epam.repository.TrainingRepository;
import com.epam.service.impl.TrainingServiceImpl;

@ExtendWith(MockitoExtension.class)
class TestTrainingService {

	@InjectMocks
	private TrainingServiceImpl trainingService;
	@Mock
	private TrainingRepository trainingRepository;
	@Mock
	private TraineeRepository traineeRepository;
	@Mock
	private TrainerRepository trainerRepository;
	@Mock
	private KafkaTemplate<String, TrainingSummaryDto> kafkaReportsService;
	private Optional<Trainee> trainee;
	private Optional<Trainer> trainer;
	private Optional<Training> training;

	@BeforeEach
	void setUp() {
		trainee = Optional
				.ofNullable(
						new Trainee(1,
								new User(1, "supriya", "divvela", "supriyadivvela9848", "1234",
										"supriyadivvela9848@gmail.com", true, LocalDate.now()),
								LocalDate.now(), "guntur",
								new ArrayList<>(List.of(new Trainer(1,
										new User(1, "supriya", "divvela", "supriyadivvela9848", "1234",
												"supriyadivvela9848@gmail.com", true, LocalDate.now()),
										new TrainingType(1, "yoga"), new HashSet<>())))));
		trainer = Optional.ofNullable(new Trainer(1, new User(1, "supriya", "divvela", "supriyadivvela9848", "1234",
				"supriyadivvela9848@gmail.com", true, LocalDate.now()), new TrainingType(1, "yoga"), new HashSet<>()));
		training = Optional.ofNullable(new Training(1, trainee.get(), trainer.get(), "Yoga Batch", LocalDate.now(), 1));
	}

	@Test
	void testAddTraining() {
		TrainingDto trainingDto = new TrainingDto("supriyadivvela9848", "supriyadivvela9848", "Yoga Batch",
				LocalDate.now(), "yoga", 1);
		training.get().setId(0);
		when(traineeRepository.findByUserUsername("supriyadivvela9848")).thenReturn(trainee);
		when(trainerRepository.findByUserUsernameAndTrainingTypeTrainingTypeNameIgnoreCase("supriyadivvela9848",
				"yoga")).thenReturn(trainer);
		when(trainingRepository.save(training.get())).thenReturn(training.get());
		trainingService.addTraining(trainingDto);
		verify(trainingRepository).save(training.get());
	}

	@Test
	void testAddTrainingWithInvalidTraiee() {
		TrainingDto trainingDto = new TrainingDto("supriyadivvela9848", "supriyadivvela9848", "Yoga Batch",
				LocalDate.now(), "yoga", 1);
		when(traineeRepository.findByUserUsername("supriyadivvela9848")).thenReturn(Optional.empty());
		assertThrows(TraineeException.class, () -> trainingService.addTraining(trainingDto));
	}

	@Test
	void testAddTrainingWithInvalidTrainer() {
		TrainingDto trainingDto = new TrainingDto("supriyadivvela9848", "supriyadivvela9848", "Yoga Batch",
				LocalDate.now(), "yoga", 1);
		when(traineeRepository.findByUserUsername("supriyadivvela9848")).thenReturn(trainee);
		when(trainerRepository.findByUserUsernameAndTrainingTypeTrainingTypeNameIgnoreCase("supriyadivvela9848",
				"yoga")).thenReturn(Optional.empty());
		assertThrows(TrainerException.class, () -> trainingService.addTraining(trainingDto));
	}

	@Test
	void testGetTraineesTrainingList() {
		List<TraineeTrainingResponse> listOfTraineeTrainingResponses = new ArrayList<>(Arrays
				.asList(new TraineeTrainingResponse("Yoga Batch", LocalDate.now(), null, 1, "supriyadivvela9848")));
		when(trainingRepository.getTraineeTrainingsList("supriyadivvela9848", LocalDate.now(), LocalDate.now(), null,
				null)).thenReturn(new ArrayList<>(List.of(training.get())));
		assertEquals(listOfTraineeTrainingResponses.toString(),
				trainingService
						.getTraineesTrainingList("supriyadivvela9848", LocalDate.now(), LocalDate.now(), null, null)
						.toString());

	}

	@Test
	void testGetTrainersTrainingList() {
		List<TrainerTrainingResponse> listOfTrainerTrainingResponse = new ArrayList<>(Arrays
				.asList(new TrainerTrainingResponse("Yoga Batch", LocalDate.now(), null, 1, "supriyadivvela9848")));
		when(trainingRepository.getTrainerTrainingsList("supriyadivvela9848", null, null, null))
				.thenReturn(List.of(training.get()));
		assertEquals(listOfTrainerTrainingResponse.toString(),
				trainingService.getTrainersTrainingList("supriyadivvela9848", null, null, null).toString());

	}
}
