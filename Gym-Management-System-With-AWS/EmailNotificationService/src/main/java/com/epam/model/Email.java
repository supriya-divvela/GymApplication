package com.epam.model;
import lombok.Data;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

import java.util.UUID;

@Data
@DynamoDbBean
public class Email {

	private UUID id;
	private String toEmail;
	private String ccEmail;
	private String body;
	private String subject;
	private String remarks;
	private String status;
	@DynamoDbPartitionKey
	public UUID getId(){
		return id;
	}
	public void setId(){
		this.id=UUID.randomUUID();
	}
}
