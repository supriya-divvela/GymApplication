package com.epam.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document("email")
public class Email {
	@Id
	private String id;
	private String toEmail;
	private String ccEmail;
	private String body;
	private String subject;
	private String remarks;
	private String status;
}
