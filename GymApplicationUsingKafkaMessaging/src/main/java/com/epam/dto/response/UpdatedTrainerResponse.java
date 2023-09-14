package com.epam.dto.response;

import java.util.List;

import com.epam.dto.TraineeDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdatedTrainerResponse {
	
	private String username;
	private String firstname;
	private String lastname;
	private String specialization;
	private boolean isActive;
	private List<TraineeDto> listOfTrainees;
}
