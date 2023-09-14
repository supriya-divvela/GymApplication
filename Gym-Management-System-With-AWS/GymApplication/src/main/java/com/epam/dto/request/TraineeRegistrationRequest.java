package com.epam.dto.request;

import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TraineeRegistrationRequest {
	
	@NotBlank(message = "firstname is required...")
	private String firstname;
	@NotBlank(message="lastname is required...")
	private String lastname;
	private LocalDate dateOfBirth;
	private String address;
	@Email(message = "email is required...")
	@NotBlank(message="Email is required..")
	private String email;
}
