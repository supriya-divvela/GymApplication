package com.epam.testservice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import com.epam.dto.TrainingTypeDto;
import com.epam.model.TrainingType;
import com.epam.repository.TrainingTypeRepository;
import com.epam.service.impl.TrainingTypeServiceImpl;

@ExtendWith(MockitoExtension.class)
class TestTrainingTypeService {
	@InjectMocks
	private TrainingTypeServiceImpl trainingTypeService;
	@Mock
	private TrainingTypeRepository trainingTypeRepository;
	@Mock
	private ModelMapper modelMapper;

	@Test
	void testGetAllTrainingTypes() {
		TrainingType trainingType = new TrainingType(1, "yoga");
		TrainingTypeDto trainingTypeDto = new TrainingTypeDto(1, "yoga");
		List<TrainingType> listOfTrainingTypes = new ArrayList<>(List.of(trainingType));
		List<TrainingTypeDto> listOfTrainingTypeDtos = new ArrayList<>(List.of(trainingTypeDto));
		when(trainingTypeRepository.findAll()).thenReturn(listOfTrainingTypes);
		when(modelMapper.map(trainingType, TrainingTypeDto.class)).thenReturn(trainingTypeDto);
		assertEquals(listOfTrainingTypeDtos.size(), trainingTypeService.getAllTrainingTypes().size());
	}

}
