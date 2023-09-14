package com.epam.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.epam.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	boolean existsByUsernameAndPassword(String username, String password);

	Optional<User> findByUsername(String username);

	void deleteByUsername(String traineeUsername);

	boolean existsByUsername(String username);

}
