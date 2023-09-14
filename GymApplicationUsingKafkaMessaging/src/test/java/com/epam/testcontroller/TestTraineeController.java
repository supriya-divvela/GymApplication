package com.epam.testcontroller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.epam.controller.TraineeController;
import com.epam.dto.Credentials;
import com.epam.dto.TrainerDto;
import com.epam.dto.request.TraineeRegistrationRequest;
import com.epam.dto.request.UpdateTraineeRequest;
import com.epam.dto.request.UpdateTraineesTrainerListRequest;
import com.epam.dto.response.TraineeProfileResponse;
import com.epam.dto.response.UpdatedTraineeResponse;
import com.epam.exceptions.TraineeException;
import com.epam.service.interfaces.TraineeService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(TraineeController.class)
class TestTraineeController {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private TraineeService traineeService;

	@Test
	void testTraineeRegistration() throws Exception {
		Credentials credentials = new Credentials();
		TraineeRegistrationRequest traineeRegistrationRequest = new TraineeRegistrationRequest();
		when(traineeService.traineeRegistration(traineeRegistrationRequest)).thenReturn(credentials);
		mockMvc.perform(post("/gym/trainee/registration").contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(traineeRegistrationRequest)))
				.andExpect(status().isCreated());
	}

	@Test
	void testTraineeRegistrationWithDataIntegrityViolationException() throws Exception {
		TraineeRegistrationRequest traineeRegistrationRequest = new TraineeRegistrationRequest();
		when(traineeService.traineeRegistration(traineeRegistrationRequest))
				.thenThrow(DataIntegrityViolationException.class);
		mockMvc.perform(post("/gym/trainee/registration").contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(traineeRegistrationRequest)))
				.andExpect(status().isBadRequest());
	}

	@Test
	void testGetTraineeProfile() throws Exception {
		TraineeProfileResponse traineeProfileResponse = new TraineeProfileResponse();
		when(traineeService.getTraineeProfile("supriya")).thenReturn(traineeProfileResponse);
		mockMvc.perform(get("/gym/trainee/" + "supriya").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	void testGetTraineeProfileWithTraineeException() throws Exception {
		when(traineeService.getTraineeProfile("supriya")).thenThrow(TraineeException.class);
		mockMvc.perform(get("/gym/trainee/" + "supriya").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	void testDeleteTraineeProfile() throws Exception {
		doNothing().when(traineeService).deleteTraineeProfile("supriya");
		mockMvc.perform(delete("/gym/trainee/" + "supriya").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	void testUpdateTraineeProfile() throws Exception {
		UpdateTraineeRequest updateTraineeRequest = new UpdateTraineeRequest();
		UpdatedTraineeResponse updatedTraineeResponse = new UpdatedTraineeResponse();
		when(traineeService.updateTraineeProfile(updateTraineeRequest)).thenReturn(updatedTraineeResponse);
		mockMvc.perform(put("/gym/trainee").contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(updateTraineeRequest))).andExpect(status().isOk());
	}

	@Test
	void testNonActiveTrainerList() throws Exception {
		List<TrainerDto> trainers = new ArrayList<>();
		when(traineeService.getNonActiveTrainersOnTrainee("supriya")).thenReturn(trainers);
		mockMvc.perform(get("/gym/trainee/nonactivetrainer/" + "supriya").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	void testUpdateTraineesTrainers() throws Exception {
		UpdateTraineesTrainerListRequest updateTraineesTrainerListRequest = new UpdateTraineesTrainerListRequest();
		List<TrainerDto> trainers = new ArrayList<>();
		when(traineeService.updateTraineesTrainers(updateTraineesTrainerListRequest)).thenReturn(trainers);
		mockMvc.perform(put("/gym/trainee/updatetraineetrainers").contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(updateTraineesTrainerListRequest)))
				.andExpect(status().isOk());
	}

}
