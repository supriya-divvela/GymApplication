package com.epam.testservice;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import com.epam.dto.TrainingSummaryDto;
import com.epam.model.TrainingSummary;
import com.epam.repository.TrainingSummaryRepository;
import com.epam.service.TrainingSummaryServiceImpl;

@ExtendWith(MockitoExtension.class)
class TestTrainingSummaryService {

	@InjectMocks
	private TrainingSummaryServiceImpl trainingSummaryService;
	@Mock
	private TrainingSummaryRepository trainingSummaryRepository;
	@Mock
	private ModelMapper modelMapper;

	@Test
	void testAddTrainingSummary() {
		TrainingSummaryDto trainingSummaryDto = new TrainingSummaryDto();
		TrainingSummary trainingSummary = new TrainingSummary();
		when(modelMapper.map(trainingSummaryDto, TrainingSummary.class)).thenReturn(trainingSummary);
		when(trainingSummaryRepository.insert(trainingSummary)).thenReturn(trainingSummary);
		trainingSummaryService.addTrainingSummary(trainingSummaryDto);
		verify(trainingSummaryRepository, times(1)).insert(trainingSummary);
	}

}
