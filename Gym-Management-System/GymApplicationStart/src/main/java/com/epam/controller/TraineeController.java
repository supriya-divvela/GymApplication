package com.epam.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.epam.dto.Credentials;
import com.epam.dto.TrainerDto;
import com.epam.dto.request.TraineeRegistrationRequest;
import com.epam.dto.request.UpdateTraineeRequest;
import com.epam.dto.request.UpdateTraineesTrainerListRequest;
import com.epam.dto.response.TraineeProfileResponse;
import com.epam.dto.response.UpdatedTraineeResponse;
import com.epam.service.interfaces.TraineeService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@RequestMapping("/gym/trainee")
@AllArgsConstructor
@RestController
public class TraineeController {
	private TraineeService traineeService;

	@PostMapping("/registration")
	public ResponseEntity<Credentials> traineeRegistrartion(
			@RequestBody @Valid TraineeRegistrationRequest traineeRegistrationRequest) {
		log.info("TraineeController : traineeRegistration ");
		return new ResponseEntity<>(traineeService.traineeRegistration(traineeRegistrationRequest), HttpStatus.CREATED);

	}

	@GetMapping("/{username}")
	public ResponseEntity<TraineeProfileResponse> getTraineeProfile(@PathVariable("username") String username) {
		log.info("TraineeController : getTraineeProfile ");
		return new ResponseEntity<>(traineeService.getTraineeProfile(username), HttpStatus.OK);
	}

	@DeleteMapping("/{username}")
	public ResponseEntity<Void> deleteTraineeProfile(@PathVariable("username") String username) {
		log.info("TraineeController : deleteTraineeProfile ");
		traineeService.deleteTraineeProfile(username);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PutMapping
	public ResponseEntity<UpdatedTraineeResponse> updateTraineeProfile(
			@RequestBody @Valid UpdateTraineeRequest updateTraineeRequest) {
		log.info("TraineeController : updateTraineeProfile ");
		return new ResponseEntity<>(traineeService.updateTraineeProfile(updateTraineeRequest), HttpStatus.OK);
	}

	@GetMapping("/nonactivetrainer/{username}")
	public ResponseEntity<List<TrainerDto>> getNonActiveTrainerList(@PathVariable("username") String username) {
		log.info("TraineeController : getNonActiveTrainerList ");
		return new ResponseEntity<>(traineeService.getNonActiveTrainersOnTrainee(username), HttpStatus.OK);

	}

	@PutMapping("/updatetraineetrainers")
	public ResponseEntity<List<TrainerDto>> updateTraineesTrainers(
			@RequestBody @Valid UpdateTraineesTrainerListRequest updateTraineesTrainerListRequest) {
		log.info("TraineeController : updateTraineesTrainer ");
		return new ResponseEntity<>(traineeService.updateTraineesTrainers(updateTraineesTrainerListRequest), HttpStatus.OK);
	}

}
