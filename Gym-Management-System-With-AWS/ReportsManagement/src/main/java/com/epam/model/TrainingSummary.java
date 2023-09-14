package com.epam.model;

import java.time.LocalDate;
import java.util.UUID;

import lombok.ToString;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

@DynamoDbBean
@ToString
public class TrainingSummary {

	private UUID id;
	private String trainerUsername;
	private String trainerFirstname;
	private String trainerLastname;
	private boolean trainerstatus;
	private LocalDate date;
	@DynamoDbPartitionKey
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public String getTrainerUsername() {
		return trainerUsername;
	}
	public void setTrainerUsername(String trainerUsername) {
		this.trainerUsername = trainerUsername;
	}
	public String getTrainerFirstname() {
		return trainerFirstname;
	}
	public void setTrainerFirstname(String trainerFirstname) {
		this.trainerFirstname = trainerFirstname;
	}
	public String getTrainerLastname() {
		return trainerLastname;
	}
	public void setTrainerLastname(String trainerLastname) {
		this.trainerLastname = trainerLastname;
	}
	public boolean isTrainerstatus() {
		return trainerstatus;
	}
	public void setTrainerstatus(boolean trainerstatus) {
		this.trainerstatus = trainerstatus;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}


}
