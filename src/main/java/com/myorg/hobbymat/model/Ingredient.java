package com.myorg.hobbymat.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("ingredients")
public class Ingredient {
    
    @Id
    String id;
    String name;
    String imageUrl;

    /*
    public Ingredient(String id, String name, String imageUrl) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
    } */

    public Ingredient(String name, String imageUrl) {
        this.name = name;
        this.imageUrl = imageUrl;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getImageUrl() {
        return imageUrl;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "Ingredient [id=" + id + ", name=" + name + ", imageUrl=" + imageUrl + "]";
    }

    
}
