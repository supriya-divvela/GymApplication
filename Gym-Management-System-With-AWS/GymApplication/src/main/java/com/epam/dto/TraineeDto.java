package com.epam.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class TraineeDto {
	@NotBlank(message = "username is required..")
	private String username;
	@NotBlank(message = "firstname is required..")
	private String firstname;
	@NotBlank(message = "lastname is required..")
	private String lastname;
}
