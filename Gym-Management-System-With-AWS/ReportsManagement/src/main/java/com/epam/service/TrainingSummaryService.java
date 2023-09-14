package com.epam.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

public interface TrainingSummaryService {

	void addTrainingSummary(String trainingSummaryDto) throws JsonMappingException, JsonProcessingException;

}
