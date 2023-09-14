package com.epam.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.epam.model.Trainer;

@Repository
public interface TrainerRepository extends JpaRepository<Trainer, Integer> {

	Optional<Trainer> findByUserUsername(String username);

	List<Trainer> findAllByUserUsername(String trainerUsername);

	Optional<Trainer> findByUserUsernameAndTrainingTypeTrainingTypeNameIgnoreCase(String trainerUsername, String trainingTypeName);
}
