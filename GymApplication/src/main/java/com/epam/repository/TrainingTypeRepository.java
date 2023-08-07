package com.epam.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.epam.model.TrainingType;

@Repository
public interface TrainingTypeRepository extends JpaRepository<TrainingType, Integer> {

	Optional<TrainingType> findByTrainingTypeNameIgnoreCase(String specialization);
}
