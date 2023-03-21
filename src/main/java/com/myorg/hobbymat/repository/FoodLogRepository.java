package com.myorg.hobbymat.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.myorg.hobbymat.model.FoodLog;

public interface FoodLogRepository extends MongoRepository<FoodLog, String> {

    public long count();
    
}
