package com.epam.dto;

import lombok.Builder;
import lombok.Data;
@Builder
@Data
public class EmailDto {
	private String toEmail;
	private String ccEmail;
	private String body;
	private String subject;
	private String remarks;
}
