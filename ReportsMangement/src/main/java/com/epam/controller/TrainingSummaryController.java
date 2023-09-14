package com.epam.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.epam.dto.TrainingSummaryDto;
import com.epam.service.TrainingSummaryServiceImpl;

import lombok.AllArgsConstructor;
@AllArgsConstructor
@RequestMapping("/training-summary")
@RestController
public class TrainingSummaryController {
	private TrainingSummaryServiceImpl traininigSummaryServiceImpl;

	@PostMapping
	public ResponseEntity<Void> addTrainingSummary(@RequestBody TrainingSummaryDto trainingSummaryDto) {
		traininigSummaryServiceImpl.addTrainingSummary(trainingSummaryDto);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
