package com.epam.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class EmailDto {
	private String toEmail;
	private String ccEmail;
	private String body;
	private String subject;
	private String remarks;
	private String status;
}
