package com.epam.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.epam.dto.TrainingTypeDto;
import com.epam.repository.TrainingTypeRepository;
import com.epam.service.interfaces.TrainingTypeService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@Service
public class TrainingTypeServiceImpl implements TrainingTypeService{
	
	private TrainingTypeRepository trainingTypeRepository; 
	private ModelMapper modelMapper;
	
	@Override
	public List<TrainingTypeDto> getAllTrainingTypes() {
		log.info("TrainingTypeServiceImpl : GetAllTrainingTypes ");
		return trainingTypeRepository.findAll().stream().map(trainingType->modelMapper.map(trainingType,TrainingTypeDto.class)).toList();
	}

}
