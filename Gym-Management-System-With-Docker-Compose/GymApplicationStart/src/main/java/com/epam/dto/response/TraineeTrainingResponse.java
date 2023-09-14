package com.epam.dto.response;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
@AllArgsConstructor
@Builder
@Data
public class TraineeTrainingResponse {
	
	private String trainingName;
	private LocalDate trainingDate;
	private String trainingType;
	private int trainingDuration;
	private String trainerUsername;

}
