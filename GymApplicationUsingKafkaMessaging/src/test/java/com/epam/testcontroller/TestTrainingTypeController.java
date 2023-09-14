package com.epam.testcontroller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.epam.controller.TrainingTypeController;
import com.epam.dto.TrainingTypeDto;
import com.epam.service.interfaces.TrainingTypeService;

@WebMvcTest(TrainingTypeController.class)
class TestTrainingTypeController {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private TrainingTypeService trainingTypeService;

	@Test
	void testGetAllTrainingType() throws Exception {
		List<TrainingTypeDto> listOfTrainingTypeDto = new ArrayList<>();
		when(trainingTypeService.getAllTrainingTypes()).thenReturn(listOfTrainingTypeDto);
		mockMvc.perform(get("/gym/trainingtype").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

}
