package com.epam.dto.response;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Data;
@Builder
@Data
public class TrainerTrainingResponse {

	private String trainingName;
	private LocalDate trainingDate;
	private String trainingType;
	private int trainingDuration;
	private String traineeUsername;
}
