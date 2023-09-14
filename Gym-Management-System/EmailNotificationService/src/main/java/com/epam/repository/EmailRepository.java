package com.epam.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.epam.model.Email;
@Repository
public interface EmailRepository extends MongoRepository<Email, Integer>{

}
