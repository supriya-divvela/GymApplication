package com.epam.service;

import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.dto.TrainingSummaryDto;
import com.epam.model.TrainingSummary;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import io.awspring.cloud.dynamodb.DynamoDbTemplate;
import io.awspring.cloud.sqs.annotation.SqsListener;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TrainingSummaryServiceImpl implements TrainingSummaryService {

	private final DynamoDbTemplate dynamoDbTemplate;
	private final ModelMapper modelMapper;

	@Autowired
	public TrainingSummaryServiceImpl(DynamoDbTemplate dynamoDbTemplate, ModelMapper modelMapper) {
		this.dynamoDbTemplate = dynamoDbTemplate;
		this.modelMapper = modelMapper;
	}

	@Override
	@SqsListener(value = "reports-queue")
	public void addTrainingSummary(String trainingSummaryDto) throws JsonMappingException, JsonProcessingException {
		log.info(trainingSummaryDto);
		TrainingSummary trainingSummary = modelMapper.map(new ObjectMapper().registerModule(new JavaTimeModule())
				.readValue(trainingSummaryDto, TrainingSummaryDto.class), TrainingSummary.class);
		trainingSummary.setId(UUID.randomUUID());
		log.info(trainingSummary.toString());
		dynamoDbTemplate.save(trainingSummary);
	}
}
