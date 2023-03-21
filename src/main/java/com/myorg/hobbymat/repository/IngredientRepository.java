package com.myorg.hobbymat.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.myorg.hobbymat.model.Ingredient;

public interface IngredientRepository extends MongoRepository<Ingredient, String> {

    public long count();

    public Ingredient findByName(String name);
    
}
