package com.epam.dto;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class TrainingSummaryDto {
	
	private String trainerUsername;
	private String trainerFirstname;
	private String trainerLastname;
	private boolean trainerstatus;
	private LocalDate date;

}
