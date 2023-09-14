package com.epam.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.epam.dto.TrainingTypeDto;
import com.epam.service.interfaces.TrainingTypeService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/gym/trainingtype")
@AllArgsConstructor
@RestController
public class TrainingTypeController {

	private TrainingTypeService trainingTypeService;

	@GetMapping
	public List<TrainingTypeDto> getAllTrainingType() {
		log.info("TrainingTypeController : GetAllTrainingType");
		return trainingTypeService.getAllTrainingTypes();
	}

}
