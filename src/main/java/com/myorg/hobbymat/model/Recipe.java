package com.myorg.hobbymat.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("recipes")
public class Recipe {

    @Id
    String id;
    String name;
    String url;
    String description;
    List<RecipeIngredient> recipeIngredients;

    public Recipe() {
        super();
    }

    public Recipe(String id, String name, String url, String description, List<RecipeIngredient> recipeIngredients) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.description = description;
        this.recipeIngredients = recipeIngredients;
    }

    public Recipe(String name, String url, String description, List<RecipeIngredient> recipeIngredients) {
        this.name = name;
        this.url = url;
        this.description = description;
        this.recipeIngredients = recipeIngredients;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public List<RecipeIngredient> getRecipeIngredients() {
        return recipeIngredients;
    }
    public void setRecipeIngredients(List<RecipeIngredient> recipeIngredients) {
        this.recipeIngredients = recipeIngredients;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Recipe [id=" + id + ", name=" + name + ", url=" + url + ", description=" + description
                + ", recipeIngredients=" + recipeIngredients + "]";
    }

    
}
