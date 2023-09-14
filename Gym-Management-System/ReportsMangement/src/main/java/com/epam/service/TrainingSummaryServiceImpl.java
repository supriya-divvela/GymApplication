package com.epam.service;

import org.modelmapper.ModelMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.epam.dto.TrainingSummaryDto;
import com.epam.model.TrainingSummary;
import com.epam.repository.TrainingSummaryRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class TrainingSummaryServiceImpl implements TrainingSummaryService {

	private TrainingSummaryRepository trainingSummaryRepository;
	private ModelMapper modelMapper;

	@Override
	@KafkaListener(topics="report-messages",groupId = "reportsapp")
	public void addTrainingSummary(TrainingSummaryDto trainingSummaryDto) {
		trainingSummaryRepository.insert(modelMapper.map(trainingSummaryDto,TrainingSummary.class));
	}

}
