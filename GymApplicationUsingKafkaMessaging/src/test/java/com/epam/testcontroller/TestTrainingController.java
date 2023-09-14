package com.epam.testcontroller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.epam.controller.TrainingController;
import com.epam.dto.TrainingDto;
import com.epam.dto.response.TraineeTrainingResponse;
import com.epam.dto.response.TrainerTrainingResponse;
import com.epam.service.interfaces.TrainingService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(TrainingController.class)
class TestTrainingController {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private TrainingService trainingService;

	@Test
	void testAddTraining() throws Exception {
		TrainingDto trainingDto = new TrainingDto();
		doNothing().when(trainingService).addTraining(trainingDto);
		mockMvc.perform(post("/gym/training/addtraining").contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(trainingDto))).andExpect(status().isOk());
	}

	@Test
	void testGetTraineesTrainersList() throws Exception {
		List<TraineeTrainingResponse> listOfTraineeTrainingResponse=new ArrayList<>();
		when(trainingService.getTraineesTrainingList("supriya",LocalDate.now(),LocalDate.now(),"","")).thenReturn(listOfTraineeTrainingResponse);
		mockMvc.perform(get("/gym/training/traineelist").param("traineeusername","supriya").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}
	

	@Test
	void testGetTrainersTraineesList() throws Exception {
		List<TrainerTrainingResponse> listOfTrainerTrainingResponse=new ArrayList<>();
		when(trainingService.getTrainersTrainingList("supriya",LocalDate.now(),LocalDate.now(),"")).thenReturn(listOfTrainerTrainingResponse);
		mockMvc.perform(get("/gym/training/trainerlist").param("trainerusername","supriya").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}


}
