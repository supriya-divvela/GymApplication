//package com.epam.testservice;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.mockito.Mockito.when;
//
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Optional;
//import java.util.Set;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//import com.epam.dto.Credentials;
//import com.epam.dto.EmailDto;
//import com.epam.dto.TraineeDto;
//import com.epam.dto.request.TrainerRegistrationRequest;
//import com.epam.dto.request.UpdateTrainerRequest;
//import com.epam.dto.response.TrainerProfileResponse;
//import com.epam.dto.response.UpdatedTrainerResponse;
//import com.epam.exceptions.TrainerException;
//import com.epam.exceptions.UserException;
//import com.epam.model.Trainee;
//import com.epam.model.Trainer;
//import com.epam.model.TrainingType;
//import com.epam.model.User;
//import com.epam.repository.TrainerRepository;
//import com.epam.repository.TrainingTypeRepository;
//import com.epam.repository.UserRepository;
//import com.epam.service.impl.TrainerServiceImpl;
//
//@ExtendWith(MockitoExtension.class)
//class TestTrainerService {
//	@InjectMocks
//	private TrainerServiceImpl trainerService;
//	@Mock
//	private UserRepository userRepository;
//	@Mock
//	private TrainerRepository trainerRepository;
//	@Mock
//	private TrainingTypeRepository trainingTypeRepository;
//	@Mock
//	private KafkaTemplate<String, EmailDto> emailKafkaService;
//	@Mock
//	private PasswordEncoder passwordEncoder;
//	private Optional<Trainee> trainee;
//	private Optional<User> user;
//	private Optional<Trainer> trainer;
//	private TrainingType trainingType;
//
//	@BeforeEach
//	void setUp() {
//		trainee = Optional
//				.ofNullable(
//						new Trainee(1,
//								new User(1, "supriya", "divvela", "supriyadivvela9848", "1234",
//										"supriyadivvela9848@gmail.com", true, LocalDate.now()),
//								LocalDate.now(), "guntur",
//								new ArrayList<>(List.of(new Trainer(1,
//										new User(1, "supriya", "divvela", "supriyadivvela9848", "1234",
//												"supriyadivvela9848@gmail.com", true, LocalDate.now()),
//										new TrainingType(1, "yoga"), new HashSet<>())))));
//		user = Optional.ofNullable(new User(1, "supriya", "divvela", "supriyadivvela9848", "1234",
//				"supriyadivvela9848@gmail.com", true, LocalDate.now()));
//		trainer = Optional.ofNullable(new Trainer(1, new User(1, "supriya", "divvela", "supriyadivvela9848", "1234",
//				"supriyadivvela9848@gmail.com", true, LocalDate.now()), new TrainingType(1, "yoga"), new HashSet<>()));
//		trainingType = new TrainingType("yoga");
//	}
//
//	@Test
//	void testGetTrainerProfile() {
//		when(trainerRepository.findByUserUsername("supriyadivvela9848")).thenReturn(trainer);
//		List<TraineeDto> listOfTraineeDtos=new ArrayList<>();
//		TrainerProfileResponse trainerProfileResponse=new TrainerProfileResponse("supriya","divvela","yoga",true,listOfTraineeDtos);
//		assertEquals(trainerProfileResponse.toString(),trainerService.getTrainerProfile("supriyadivvela9848").toString());
//	}
//
//	@Test
//	void testGetTrainerProfileWithFullData() {
//		trainer.get().setListOfTrainees(Set.of(trainee.get()));
//		when(trainerRepository.findByUserUsername("supriyadivvela9848")).thenReturn(trainer);
//		List<TraineeDto> listOfTraineeDtos = new ArrayList<>(
//				List.of(new TraineeDto("supriyadivvela9848", "supriya", "divvela")));
//		TrainerProfileResponse trainerProfileResponse = new TrainerProfileResponse("supriya", "divvela", "yoga", true,
//				listOfTraineeDtos);
//		assertEquals(trainerProfileResponse.toString(),
//				trainerService.getTrainerProfile("supriyadivvela9848").toString());
//	}
//
//	@Test
//	void testGetTrainerProfileWithInvalidTrainer() {
//		when(trainerRepository.findByUserUsername("supriyadivvela9848")).thenReturn(Optional.empty());
//		assertThrows(TrainerException.class,()->trainerService.getTrainerProfile("supriyadivvela9848"));
//	}
//
//	@Test
//	void testTrainerRegistration() {
//		TrainerRegistrationRequest trainerRegistrationRequest = new TrainerRegistrationRequest("supriya", "divvela",
//				"supriyadivvela9848@gmail.com", "yoga");
//		Credentials credentials = new Credentials("supriyadivvela9848", "1234");
//		when(passwordEncoder.encode("1234")).thenReturn("1234");
//		user.get().setId(0);
//		user.get().setCreatedAt(null);
//		user.get().setActive(false);
//		when(userRepository.save(user.get())).thenReturn(user.get());
//		when(trainingTypeRepository.findByTrainingTypeNameIgnoreCase("yoga")).thenReturn(trainingType);
//		assertEquals(credentials.toString(), trainerService.trainerRegistration(trainerRegistrationRequest).toString());
//	}
//
//	@Test
//	void testUpdateTrainerProfile() {
//		trainer.get().setListOfTrainees(Set.of(trainee.get()));
//		;
//		UpdateTrainerRequest updateTrainerRequest = new UpdateTrainerRequest("supriyadivvela9848", "supriya", "divvela",
//				"yoga", true);
//		when(userRepository.findByUsername("supriyadivvela9848")).thenReturn(user);
//		when(userRepository.save(user.get())).thenReturn(user.get());
//		when(trainerRepository.findByUserUsername("supriyadivvela9848")).thenReturn(trainer);
//		when(trainingTypeRepository.findByTrainingTypeNameIgnoreCase("yoga")).thenReturn(trainingType);
//		when(trainerRepository.save(trainer.get())).thenReturn(trainer.get());
//		List<TraineeDto> listOfTraineeDtos = new ArrayList<>(
//				List.of(new TraineeDto("supriyadivvela9848", "supriya", "divvela")));
//		UpdatedTrainerResponse updatedTrainerResponse = new UpdatedTrainerResponse("supriyadivvela9848", "supriya",
//				"divvela", "yoga", true, listOfTraineeDtos);
//		assertEquals(updatedTrainerResponse.toString(),
//				trainerService.updateTrainerProfile(updateTrainerRequest).toString());
//	}
//
//	@Test
//	void testUpdateTrainerProfileWithInvalidUser() {
//		trainer.get().setListOfTrainees(Set.of(trainee.get()));
//		UpdateTrainerRequest updateTrainerRequest = new UpdateTrainerRequest("supriyadivvela9848", "supriya", "divvela",
//				"yoga", true);
//		when(userRepository.findByUsername("supriyadivvela9848")).thenReturn(Optional.empty());
//		assertThrows(UserException.class, () -> trainerService.updateTrainerProfile(updateTrainerRequest));
//	}
//
//	@Test
//	void testUpdateTrainerProfileWithInvalidTrainer() {
//		trainer.get().setListOfTrainees(Set.of(trainee.get()));
//		UpdateTrainerRequest updateTrainerRequest = new UpdateTrainerRequest("supriyadivvela9848", "supriya", "divvela",
//				"yoga", true);
//		when(userRepository.findByUsername("supriyadivvela9848")).thenReturn(user);
//		when(userRepository.save(user.get())).thenReturn(user.get());
//		when(trainerRepository.findByUserUsername("supriyadivvela9848")).thenReturn(Optional.empty());
//		assertThrows(TrainerException.class,
//				() -> trainerService.updateTrainerProfile(updateTrainerRequest));
//	}
//
//}
