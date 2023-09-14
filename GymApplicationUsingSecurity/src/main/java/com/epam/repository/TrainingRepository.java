package com.epam.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.epam.model.Training;

@Repository
public interface TrainingRepository extends JpaRepository<Training, Integer> {

	void deleteByTraineeId(int id);

	List<Training> findAllByTraineeId(int id);

	List<Training> findAllByTrainerId(int id);

	@Query("SELECT t FROM Training t " + "JOIN t.trainee tr " + "JOIN t.trainer tnr " + "JOIN t.trainingType tt "
			+ "WHERE tr.user.username = :username " + "AND (:periodFrom IS NULL OR t.trainingDate >= :periodFrom) "
			+ "AND (:periodTo IS NULL OR t.trainingDate <= :periodTo) "
			+ "AND (:trainerName IS NULL OR tnr.user.username LIKE %:trainerName%) "
			+ "AND (:trainingType IS NULL OR tt.trainingTypeName LIKE %:trainingType%)")
	List<Training> getTraineeTrainingsList(String username, LocalDate periodFrom, LocalDate periodTo,
			String trainerName, String trainingType);

	@Query("SELECT t FROM Training t " + "JOIN t.trainee tr " + "JOIN t.trainer tnr " + "JOIN t.trainingType tt "
			+ "WHERE tr.user.username = :username " + "AND (:periodFrom IS NULL OR t.trainingDate >= :periodFrom) "
			+ "AND (:periodTo IS NULL OR t.trainingDate <= :periodTo) "
			+ "AND (:trainerName IS NULL OR tnr.user.username LIKE %:trainerName%) ")
	List<Training> getTrainerTrainingsList(String username, LocalDate periodFrom, LocalDate periodTo,
			String trainerName);

}
