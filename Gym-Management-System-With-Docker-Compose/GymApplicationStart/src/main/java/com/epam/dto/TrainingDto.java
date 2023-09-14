package com.epam.dto;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TrainingDto {
	@NotBlank(message="trainee username is required..")
	private String traineeUsername;
	@NotBlank(message="trainer username is required..")
	private String trainerUsername;
	@NotBlank(message="training name is required..")
	private String trainingName;
	@DateTimeFormat(pattern = "DD-MM-YYY")
	private LocalDate trainingDate;
	@NotBlank(message="training type is required..")
	private String trainingType;
	@Min(1)
	private int trainingDuration;
}
