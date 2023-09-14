package com.epam.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.epam.model.TrainingSummary;

@Repository
public interface TrainingSummaryRepository extends MongoRepository<TrainingSummary, Integer> {

}
