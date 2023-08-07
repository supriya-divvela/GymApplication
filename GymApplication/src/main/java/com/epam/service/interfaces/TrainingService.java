package com.epam.service.interfaces;

import java.time.LocalDate;
import java.util.List;

import com.epam.dto.TrainingDto;
import com.epam.dto.response.TraineeTrainingResponse;
import com.epam.dto.response.TrainerTrainingResponse;

public interface TrainingService {
	
	void addTraining(TrainingDto trainingDto);

	List<TraineeTrainingResponse> getTraineesTrainingList(String username, LocalDate periodFrom,LocalDate periodTo,String trainerName,String trainingType);

	List<TrainerTrainingResponse> getTrainersTrainingList(String username, LocalDate periodFrom,LocalDate periodTo,String traineeName);

}
