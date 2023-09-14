package com.epam.dto.request;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UpdateTraineesTrainerListRequest {
	
	@NotBlank(message = "trainee username is required..")
	private String traineeUsername;
	@NotNull(message="Atleast one trainer should be add..")
	private List<String> listOfTrainerUsernames;

}
