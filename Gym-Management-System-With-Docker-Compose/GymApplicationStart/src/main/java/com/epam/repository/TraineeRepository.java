package com.epam.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.epam.model.Trainee;
import com.epam.model.User;

@Repository
public interface TraineeRepository extends JpaRepository<Trainee, Integer> {

	Optional<Trainee> findByUser(User user);

	void deleteByUser(User user);

	Optional<Trainee> findByUserUsername(String traineeUsername);

	void deleteByUserUsername(String traineeUsername);
}
