package com.epam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.epam.model.TrainingType;

@Repository
public interface TrainingTypeRepository extends JpaRepository<TrainingType, Integer> {

	TrainingType findByTrainingTypeNameIgnoreCase(String specialization);
}
