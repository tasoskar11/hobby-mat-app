package com.myorg.hobbymat.model;

import org.springframework.data.mongodb.core.mapping.DocumentReference;

public class RecipeIngredient {

    @DocumentReference
    Ingredient ingredient;

    Double quantity;
    Double priceEstimation;
    Double caloryEstimation;

    public RecipeIngredient() {
        super();
    }

    public RecipeIngredient(Ingredient ingredient, Double quantity, Double priceEstimation, Double caloryEstimation) {
        this.ingredient = ingredient;
        this.quantity = quantity;
        this.priceEstimation = priceEstimation;
        this.caloryEstimation = caloryEstimation;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }
    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }
    public Double getQuantity() {
        return quantity;
    }
    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }
    public Double getPriceEstimation() {
        return priceEstimation;
    }
    public void setPriceEstimation(Double priceEstimation) {
        this.priceEstimation = priceEstimation;
    }
    public Double getCaloryEstimation() {
        return caloryEstimation;
    }
    public void setCaloryEstimation(Double caloryEstimation) {
        this.caloryEstimation = caloryEstimation;
    }
}
