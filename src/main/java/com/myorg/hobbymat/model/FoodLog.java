package com.myorg.hobbymat.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("foodlogs")
public class FoodLog {

    @Id
    String id;

    LocalDateTime date;
    Recipe recipe;
 
    public FoodLog() {
        super();
    }

    public FoodLog(String id, LocalDateTime date, Recipe recipe) {
        super();
        this.id = id;
        this.date = date;
        this.recipe = recipe;

    }

    public FoodLog(LocalDateTime date, Recipe recipe) {

        this.date = date;
        this.recipe = recipe;

    }
    
    public LocalDateTime getDate() {
        return date;
    }
    public void setDate(LocalDateTime date) {
        this.date = date;
    }
    public Recipe getRecipe() {
        return recipe;
    }
    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
}
