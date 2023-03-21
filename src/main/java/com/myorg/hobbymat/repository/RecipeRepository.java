package com.myorg.hobbymat.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.myorg.hobbymat.model.Recipe;

public interface RecipeRepository extends MongoRepository<Recipe, String> {

    public long count();
    
}
