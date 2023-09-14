package com.epam.dto.response;

import java.time.LocalDate;
import java.util.List;

import com.epam.dto.TrainerDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TraineeProfileResponse {
	
	
	private String firstname;
	private String lastname;
	private LocalDate dataOfBirth;
	private String address;
	private boolean isActive;
	private List<TrainerDto> listOfTrainers;
}
