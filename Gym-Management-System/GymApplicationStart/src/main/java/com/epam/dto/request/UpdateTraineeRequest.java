package com.epam.dto.request;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateTraineeRequest {
	@NotBlank(message = "username is required..")
	private String username;
	@NotBlank(message = "firstname is required..")
	private String firstname;
	@NotBlank(message = "lastname is required..")
	private String lastname;
	@DateTimeFormat(pattern = "DD-MM-YYYY")
	private LocalDate dateOfBirth;
	@NotBlank(message = "address is required..")
	private String address;
	@Pattern(regexp = "^(?i)(True|False)$", message = "Activity status must be either True or False")
	private boolean isActive;
}
