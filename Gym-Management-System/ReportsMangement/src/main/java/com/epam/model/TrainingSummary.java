package com.epam.model;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(collection = "training_summary")
public class TrainingSummary {
	
	@Id
	private String id; 
	private String trainerUsername;
	private String trainerFirstname;
	private String trainerLastname;
	private boolean trainerstatus;
	private LocalDate date;;

}
