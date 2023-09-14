package com.epam.feign;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.epam.dto.TrainingSummaryDto;

import jakarta.servlet.http.HttpServletResponse;

public class ReportsClientImpl implements ReportsClient{

	@Override
	public ResponseEntity<Void> addTrainingSummary(TrainingSummaryDto trainingSummaryDto) {
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity<Void> getAllTrainings(String trainerUsername, HttpServletResponse response) {
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

}
