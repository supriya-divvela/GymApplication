package com.epam.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@NoArgsConstructor
@Data
@Entity
public class Training {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ManyToOne
	@JoinColumn(name = "trainee_id", referencedColumnName = "id")
	private Trainee trainee;
	@ManyToOne
	@JoinColumn(name = "trainer_id", referencedColumnName = "id")
	private Trainer trainer;
	@Column(unique=true)
	private String trainingName;
	private LocalDate trainingDate;
	private int duration;
}
