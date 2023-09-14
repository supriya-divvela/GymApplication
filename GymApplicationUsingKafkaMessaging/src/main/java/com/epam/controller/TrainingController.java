package com.epam.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.epam.dto.TrainingDto;
import com.epam.dto.response.TraineeTrainingResponse;
import com.epam.dto.response.TrainerTrainingResponse;
import com.epam.service.interfaces.TrainingService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/gym/training")
@AllArgsConstructor
@RestController
public class TrainingController {
	private TrainingService trainingService;

	@PostMapping("/addtraining")
	public ResponseEntity<Void> addTraining(@RequestBody @Valid TrainingDto trainingDto) {
		log.info("TrainingController : addTraining ");
		trainingService.addTraining(trainingDto);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping("/traineelist")
	public ResponseEntity<List<TraineeTrainingResponse>> getTraineesTrainersList(
			@RequestParam(value = "traineeusername") String username,
			@RequestParam(value = "periodfrom", required = false) LocalDate periodFrom,
			@RequestParam(value = "periodto", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate periodTo,
			@RequestParam(value = "trainername", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) String trainerName,
			@RequestParam(value = "trainingtype", required = false) String trainingType) {
		log.info("TrainingController : getTraineesTrainersList ");
		return new ResponseEntity<>(
				trainingService.getTraineesTrainingList(username, periodFrom, periodTo, trainerName, trainingType),
				HttpStatus.OK);

	}

	@GetMapping("/trainerlist")
	public ResponseEntity<List<TrainerTrainingResponse>> getTrainersTraineesList(
			@RequestParam("trainerusername") String username,
			@RequestParam(value = "periodfrom", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate periodFrom,
			@RequestParam(value = "periodto", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate periodTo,
			@RequestParam(value = "trainername", required = false) String traineeName) {
		log.info("TrainingController : getTrainersTraineesList ");
		return new ResponseEntity<>(
				trainingService.getTrainersTrainingList(username, periodFrom, periodTo, traineeName), HttpStatus.OK);

	}
}
