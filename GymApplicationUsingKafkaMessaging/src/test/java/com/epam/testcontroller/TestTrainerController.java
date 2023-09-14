package com.epam.testcontroller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.epam.controller.TrainerController;
import com.epam.dto.Credentials;
import com.epam.dto.request.TrainerRegistrationRequest;
import com.epam.dto.request.UpdateTrainerRequest;
import com.epam.dto.response.TrainerProfileResponse;
import com.epam.dto.response.UpdatedTrainerResponse;
import com.epam.exceptions.TrainerException;
import com.epam.service.interfaces.TrainerService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(TrainerController.class)
class TestTrainerController {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private TrainerService trainerService;

	@Test
	void testTrainerRegistration() throws Exception {
		Credentials credentials = new Credentials();
		TrainerRegistrationRequest trainerRegistrationRequest = new TrainerRegistrationRequest();
		when(trainerService.trainerRegistration(trainerRegistrationRequest)).thenReturn(credentials);
		mockMvc.perform(post("/gym/trainer/registration").contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(trainerRegistrationRequest)))
				.andExpect(status().isCreated());
	}
	
	@Test
	void testTrainerRegistrationWithRuntimeException() throws Exception {
		Credentials credentials = new Credentials();
		TrainerRegistrationRequest trainerRegistrationRequest = new TrainerRegistrationRequest();
		when(trainerService.trainerRegistration(trainerRegistrationRequest)).thenReturn(credentials);
		mockMvc.perform(post("/gym/trainer/registration").contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(null)))
				.andExpect(status().isInternalServerError());
	}

	@Test
	void testGetTrainerProfile() throws Exception {
		TrainerProfileResponse trainerProfileResponse = new TrainerProfileResponse();
		when(trainerService.getTrainerProfile("supriya")).thenReturn(trainerProfileResponse);
		mockMvc.perform(get("/gym/trainer/" + "supriya").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}
	
	@Test
	void testGetTrainerProfileWithTrainerException() throws Exception {
		when(trainerService.getTrainerProfile("supriya")).thenThrow(TrainerException.class);
		mockMvc.perform(get("/gym/trainer/" + "supriya").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	void testUpdateTrainerProfile() throws Exception {
		UpdateTrainerRequest updateTrainerRequest = new UpdateTrainerRequest();
		UpdatedTrainerResponse updatedTraineeResponse = new UpdatedTrainerResponse();
		when(trainerService.updateTrainerProfile(updateTrainerRequest)).thenReturn(updatedTraineeResponse);
		mockMvc.perform(put("/gym/trainer").contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(updateTrainerRequest))).andExpect(status().isOk());
	}

}
