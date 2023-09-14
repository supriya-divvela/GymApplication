package com.epam.dto;

import lombok.Data;

@Data
public class EmailDto {
	private String toEmail;
	private String ccEmail;
	private String body;
	private String subject;
	private String remarks;
	private String status;
}
