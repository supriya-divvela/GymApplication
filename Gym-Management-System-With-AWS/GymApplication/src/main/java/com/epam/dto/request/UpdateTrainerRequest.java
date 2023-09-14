package com.epam.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UpdateTrainerRequest {
	
	@NotBlank(message = "username is required..")
	private String username;
	@NotBlank(message = "firstname is required..")
	private String firstname;
	@NotBlank(message = "lastname is required..")
	private String lastname;
	@Pattern(regexp = "^(?i)(fitness|yoga|zumba|stretching|resistance)$", message = "specialization should be either fitness or zumba or stretching or resistance")
	private String specialization;
	@Pattern(regexp = "^(?i)(True|False)$", message = "Activity status must be either True or False")
	private boolean isActive;
}
