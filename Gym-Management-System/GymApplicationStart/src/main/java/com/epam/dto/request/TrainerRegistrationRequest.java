package com.epam.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TrainerRegistrationRequest {
	
	@NotBlank(message = "firstname is required..")
	private String firstname;
	@NotBlank(message = "lastname is required..")
	private String lastname;
	@Email(message = "email is required..")
	@NotBlank(message="message is required..")
	private String email;
	@Pattern(regexp = "^(?i)(fitness|yoga|zumba|stretching|resistance)$", message = "specialization should be either fitness or zumba or stretching or resistance")
	private String specialization;
}
