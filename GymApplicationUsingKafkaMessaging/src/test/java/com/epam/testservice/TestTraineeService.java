//package com.epam.testservice;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.mockito.Mockito.doNothing;
//import static org.mockito.Mockito.times;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Optional;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.dao.DataIntegrityViolationException;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//import com.epam.dto.Credentials;
//import com.epam.dto.EmailDto;
//import com.epam.dto.TrainerDto;
//import com.epam.dto.request.TraineeRegistrationRequest;
//import com.epam.dto.request.UpdateTraineeRequest;
//import com.epam.dto.request.UpdateTraineesTrainerListRequest;
//import com.epam.dto.response.TraineeProfileResponse;
//import com.epam.dto.response.UpdatedTraineeResponse;
//import com.epam.exceptions.TraineeException;
//import com.epam.exceptions.UserException;
//import com.epam.model.Trainee;
//import com.epam.model.Trainer;
//import com.epam.model.TrainingType;
//import com.epam.model.User;
//import com.epam.repository.TraineeRepository;
//import com.epam.repository.TrainerRepository;
//import com.epam.repository.TrainingRepository;
//import com.epam.repository.UserRepository;
//import com.epam.service.impl.TraineeServiceImpl;
//
//@ExtendWith(MockitoExtension.class)
//class TestTraineeService {
//
//	@InjectMocks
//	private TraineeServiceImpl traineeService;
//	@Mock
//	private TraineeRepository traineeRepository;
//	@Mock
//	private UserRepository userRepository;
//	@Mock
//	private TrainerRepository trainerRepository;
//	@Mock
//	private TrainingRepository trainingRepository;
//	@Mock
//	private KafkaTemplate<String, EmailDto> emailKafkaService;
//	@Mock
//	private PasswordEncoder passwordEncoder;
//	private Optional<Trainee> trainee;
//	private Optional<User> user;
//	private Optional<Trainer> trainer;
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
//
//	}
//
//	@Test
//	void testGetTraineeProfile() {
//		TraineeProfileResponse traineeProfileResponse = new TraineeProfileResponse("supriya", "divvela",
//				LocalDate.now(), "guntur", true,
//				new ArrayList<>(List.of(new TrainerDto("supriyadivvela9848", "supriya", "divvela", "yoga"))));
//		when(traineeRepository.findByUserUsername("supriya")).thenReturn(trainee);
//		assertEquals(traineeProfileResponse.toString(), traineeService.getTraineeProfile("supriya").toString());
//	}
//
//	@Test
//	void testGetTraineeProfileWithInvalidUser() {
//		when(traineeRepository.findByUserUsername("supriya")).thenReturn(Optional.empty());
//		assertThrows(TraineeException.class,()-> traineeService.getTraineeProfile("supriya"));
//	}
//
//	@Test
//	void testTraineeRegistration() {
//		Credentials credentials = new Credentials("supriyadivvela9848", "1234");
//		TraineeRegistrationRequest traineeRegistrationRequest = new TraineeRegistrationRequest("supriya", "divvela",
//				LocalDate.now(), "guntur", "supriyadivvela9848@gmail.com");
//		when(passwordEncoder.encode("1234")).thenReturn("1234");
//		assertEquals(credentials.toString(), traineeService.traineeRegistration(traineeRegistrationRequest).toString());
//	}
//
//	@Test
//	void testTraineeRegistrationWithDataIntegrityViolationException() {
//		TraineeRegistrationRequest traineeRegistrationRequest = new TraineeRegistrationRequest("supriya", "divvela",
//				LocalDate.now(), "guntur", "supriyadivvela9848@gmail.com");
//		Trainee trainee = Trainee.builder().address(traineeRegistrationRequest.getAddress())
//				.dateOfBirth(traineeRegistrationRequest.getDateOfBirth()).user(null).build();
//		when(passwordEncoder.encode("1234")).thenReturn("1234");
//		when(traineeRepository.save(trainee)).thenThrow(DataIntegrityViolationException.class);
//		assertThrows(DataIntegrityViolationException.class,
//				() -> traineeService.traineeRegistration(traineeRegistrationRequest));
//	}
//
//	@Test
//	void testDeleteTraineeProfile() {
//		when(traineeRepository.findByUserUsername("supriyadivvela9848")).thenReturn(trainee);
//		doNothing().when(trainingRepository).deleteAllByTraineeId(1);
//		doNothing().when(traineeRepository).delete(trainee.get());
//		doNothing().when(userRepository).deleteByUsername("supriyadivvela9848");
//		traineeService.deleteTraineeProfile("supriyadivvela9848");
//		verify(trainingRepository, times(1)).deleteAllByTraineeId(1);
//		verify(traineeRepository, times(1)).delete(trainee.get());
//		verify(userRepository, times(1)).deleteByUsername("supriyadivvela9848");
//	}
//
//	@Test
//	void testDeleteTraineeProfileWithInvalidUsername() {
//		when(traineeRepository.findByUserUsername("supriyadivvela9848")).thenReturn(Optional.empty());
//		assertThrows(TraineeException.class,()->traineeService.deleteTraineeProfile("supriyadivvela9848"));
//
//	}
//
//	@Test
//	void testUpdateTraineeProfile() {
//		UpdateTraineeRequest updateTraineeRequest = new UpdateTraineeRequest("supriyadivvela9848", "supriya", "divvela",
//				LocalDate.now(), "guntur", true);
//		UpdatedTraineeResponse updatedTraineeResponse = new UpdatedTraineeResponse("supriyadivvela9848", "supriya",
//				"divvela", LocalDate.now(), "guntur", true,
//				new ArrayList<>(List.of(new TrainerDto("supriyadivvela9848", "supriya", "divvela", "yoga"))));
//		when(userRepository.findByUsername("supriyadivvela9848")).thenReturn(user);
//		when(userRepository.save(user.get())).thenReturn(user.get());
//		when(traineeRepository.save(trainee.get())).thenReturn(trainee.get());
//		when(traineeRepository.findByUserUsername("supriyadivvela9848")).thenReturn(trainee);
//		assertEquals(updatedTraineeResponse.toString(),
//				traineeService.updateTraineeProfile(updateTraineeRequest).toString());
//	}
//
//	@Test
//	void testUpdateTraineeProfileWithInvalidUsername() {
//		UpdateTraineeRequest updateTraineeRequest = new UpdateTraineeRequest("supriyadivvela9848", "supriya", "divvela",
//				LocalDate.now(), "guntur", false);
//		when(userRepository.findByUsername("supriyadivvela9848")).thenReturn(Optional.empty());
//		assertThrows(UserException.class, () -> traineeService.updateTraineeProfile(updateTraineeRequest));
//	}
//
//	@Test
//	void testUpdateTraineeProfileWithInvalidTrainee() {
//		UpdateTraineeRequest updateTraineeRequest = new UpdateTraineeRequest("supriyadivvela9848", "supriya", "divvela",
//				LocalDate.now(), "guntur", true);
//		when(userRepository.findByUsername("supriyadivvela9848")).thenReturn(user);
//		when(userRepository.save(user.get())).thenReturn(user.get());
//		when(traineeRepository.findByUserUsername("supriyadivvela9848")).thenReturn(Optional.empty());
//		assertThrows(TraineeException.class, () -> traineeService.updateTraineeProfile(updateTraineeRequest));
//	}
//
//	@Test
//	void testUpdateTraineesTrainersWithInvalidTrainer() {
//		UpdateTraineesTrainerListRequest updateTraineesTrainerListRequest = new UpdateTraineesTrainerListRequest(
//				"supriyadivvela9848", new ArrayList<>());
//		when(traineeRepository.findByUserUsername("supriyadivvela9848")).thenReturn(Optional.empty());
//		assertThrows(TraineeException.class,
//				() -> traineeService.updateTraineesTrainers(updateTraineesTrainerListRequest));
//	}
//
//	@Test
//	void testUpdateTraineesTrainers() {
//		UpdateTraineesTrainerListRequest updateTraineesTrainerListRequest = new UpdateTraineesTrainerListRequest(
//				"supriyadivvela9848", new ArrayList<>(List.of("supriyadivvela9848")));
//		List<TrainerDto> listOfTrainerDtos = new ArrayList<>(
//				List.of(new TrainerDto("supriyadivvela9848", "supriya", "divvela", "yoga")));
//		when(traineeRepository.findByUserUsername("supriyadivvela9848")).thenReturn(trainee);
//		when(traineeRepository.save(trainee.get())).thenReturn(trainee.get());
//		when(trainerRepository.findByUserUsername("supriyadivvela9848")).thenReturn(trainer);
//		assertEquals(listOfTrainerDtos.toString(),
//				traineeService.updateTraineesTrainers(updateTraineesTrainerListRequest).toString());
//	}
//
//	@Test
//	void testGetNonActiveTrainersOnTraineeWithInvalidTrainee() {
//		when(traineeRepository.findByUserUsername("supriya")).thenReturn(Optional.empty());
//		assertThrows(TraineeException.class,()->traineeService.getNonActiveTrainersOnTrainee("supriya"));
//	}
//
//	@Test
//	void testGetNonActiveTrainersOnTrainee() {
//		when(traineeRepository.findByUserUsername("supriyadivvela9848")).thenReturn(trainee);
//		when(trainerRepository.findAll()).thenReturn(new ArrayList<>(Arrays.asList(trainer.get())));
//		List<TrainerDto> listOfTrainerDtos = new ArrayList<>(
//				List.of(new TrainerDto("supriyadivvela9848", "supriya", "divvela", "yoga")));
//		assertEquals(listOfTrainerDtos.toString(),traineeService.getNonActiveTrainersOnTrainee("supriyadivvela9848").toString());
//	}
//
//	@Test
//	void testGetNonActiveTrainersOnTraineeWithMoreThanOneTraineesInTrainers() {
//		Optional<Trainer> trainer2 = Optional
//				.ofNullable(
//						new Trainer(2,
//								new User(2, "siva", "divvela", "sivadivvela9848", "1234", "sivadivvela9848@gmail.com",
//										true, LocalDate.now()),
//								new TrainingType(2, "zumba"), new HashSet<>(List.of(trainee.get()))));
//		trainer.get().getListOfTrainees().add(trainee.get());
//		when(traineeRepository.findByUserUsername("supriyadivvela9848")).thenReturn(trainee);
//		when(trainerRepository.findAll()).thenReturn(new ArrayList<>(List.of(trainer.get(), trainer2.get())));
//		List<TrainerDto> listOfTrainerDtos = new ArrayList<>();
//		assertEquals(listOfTrainerDtos.toString(),
//				traineeService.getNonActiveTrainersOnTrainee("supriyadivvela9848").toString());
//	}
//
//	@Test
//	void testGetNonActiveTrainersOnTraineeWithEmptyTrainers() {
//		when(traineeRepository.findByUserUsername("supriyadivvela9848")).thenReturn(trainee);
//		when(trainerRepository.findAll()).thenReturn(new ArrayList<>());
//		List<TrainerDto> listOfTrainerDtos = new ArrayList<>();
//		assertEquals(listOfTrainerDtos.toString(),traineeService.getNonActiveTrainersOnTrainee("supriyadivvela9848").toString());
//	}
//
//}
