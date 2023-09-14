package com.epam.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.epam.dto.Credentials;
import com.epam.dto.request.TrainerRegistrationRequest;
import com.epam.dto.request.UpdateTrainerRequest;
import com.epam.dto.response.TrainerProfileResponse;
import com.epam.dto.response.UpdatedTrainerResponse;
import com.epam.service.interfaces.TrainerService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@RequestMapping("/gym/trainer")
@AllArgsConstructor
@RestController
public class TrainerController {
	private TrainerService trainerService;

	@PostMapping("/registration")
	public ResponseEntity<Credentials> trainerRegistrartion(@RequestBody @Valid TrainerRegistrationRequest trainerRegistrationRequest) {
		log.info("TrainerController : trainerRegistration ");
		return new ResponseEntity<>(trainerService.trainerRegistration(trainerRegistrationRequest), HttpStatus.CREATED);

	}

	@GetMapping("/{username}")
	public ResponseEntity<TrainerProfileResponse> getTrainerProfile(@PathVariable("username") String username) {
		log.info("TrainerController : getTrainerProfile ");
		return new ResponseEntity<>(trainerService.getTrainerProfile(username), HttpStatus.OK);
	}

	@PutMapping
	public ResponseEntity<UpdatedTrainerResponse> updateTrainerProfile(
			@RequestBody @Valid UpdateTrainerRequest updateTrainerRequest) {
		log.info("TrainerController : updateTrainerProfile ");
		return new ResponseEntity<>(trainerService.updateTrainerProfile(updateTrainerRequest), HttpStatus.OK);
	}
}
