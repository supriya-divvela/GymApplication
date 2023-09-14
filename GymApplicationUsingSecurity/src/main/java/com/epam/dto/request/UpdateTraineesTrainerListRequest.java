package com.epam.dto.request;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateTraineesTrainerListRequest {
	
	@NotBlank(message = "trainee username is required..")
	private String traineeUsername;
	@NotNull(message="Atleast one trainer should be add..")
	private List<String> listOfTrainerUsernames;

}
