package com.myorg.hobbymat.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myorg.hobbymat.model.Ingredient;
import com.myorg.hobbymat.model.Recipe;
import com.myorg.hobbymat.model.RecipeIngredient;
import com.myorg.hobbymat.repository.IngredientRepository;
import com.myorg.hobbymat.repository.RecipeRepository;
import java.lang.invoke.MethodHandles;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/recipes")
public class RecipeController {
    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());


    @Autowired
    RecipeRepository recipeRepository;

    @Autowired
    IngredientRepository ingredientRepository;

    @GetMapping
    public List<Recipe> getRecipes() {
        return recipeRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Recipe> register(@RequestBody Recipe entry) throws URISyntaxException {
        Recipe saved = recipeRepository.save(entry);
        return ResponseEntity.created(new URI("/recipes/" + saved.getId())).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Recipe> updateRecipe(@PathVariable String id, @RequestBody Recipe entry) {
        Recipe current = recipeRepository.findById(id).orElseThrow(RuntimeException::new);
        current.setName(entry.getName());
        current.setDescription(entry.getDescription());
        current.setUrl(entry.getUrl());
        current.setRecipeIngredients(entry.getRecipeIngredients());
        current = recipeRepository.save(entry);
        return ResponseEntity.ok(current);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Recipe> deleteRecipe(@PathVariable String id) {
        recipeRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Recipe> getRecipe(@PathVariable String id) {
        Recipe current = recipeRepository.findById(id).orElseThrow(RuntimeException::new);
        return ResponseEntity.ok(current);
    }

    @PostMapping(path = "/registerCarbonara", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> initializeWithIngredients() {
        Ingredient bacon = ingredientRepository.findByName("bacon");
        Ingredient farfalle = ingredientRepository.findByName("farfalle");
        Ingredient egg = ingredientRepository.findByName("ägg");
        List<RecipeIngredient> ingredients = new ArrayList();
        ingredients.add(new RecipeIngredient(egg, 120.0, 9.0, null));
        ingredients.add(new RecipeIngredient(farfalle, 500.0, 15.0, null));
        ingredients.add(new RecipeIngredient(bacon, 240.0, 32.0, null));
        Recipe carbonara = new Recipe("carbonara", "some url", "simple traditional carbonara", ingredients);
        recipeRepository.save(carbonara);
        return ResponseEntity.ok("");
    }

    @PostMapping(path = "/registerArtor", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> initializeArtorWithIngredients() {
        Ingredient peas = ingredientRepository.findByName("ärtor");
        Ingredient potatoes = ingredientRepository.findByName("potatis");
        Ingredient onion = ingredientRepository.findByName("lök");
        Ingredient garlic = ingredientRepository.findByName("vitlök");
        Ingredient persilja = ingredientRepository.findByName("persilja");
        Ingredient dill = ingredientRepository.findByName("dill");
        Ingredient mynta = ingredientRepository.findByName("mynta");
        Ingredient carrots = ingredientRepository.findByName("morötter");
        Ingredient oliveOil = ingredientRepository.findByName("olivolja");

        List<RecipeIngredient> ingredients = new ArrayList();
        ingredients.add(new RecipeIngredient(peas, 500.0, 23.0, null));
        ingredients.add(new RecipeIngredient(potatoes, 350.0, 5.0, null));
        ingredients.add(new RecipeIngredient(onion, 100.0, 1.0, null));
        ingredients.add(new RecipeIngredient(garlic, 5.0, 1.0, null));
        ingredients.add(new RecipeIngredient(persilja, 5.0, 1.0, null));
        ingredients.add(new RecipeIngredient(dill, 5.0, 1.0, null));
        ingredients.add(new RecipeIngredient(mynta, 5.0, 1.0, null));
        ingredients.add(new RecipeIngredient(carrots, 100.0, 2.0, null));
        ingredients.add(new RecipeIngredient(oliveOil, 50.0, 1.0, null));
        Recipe arakas = new Recipe("ärtorgryta", "some url", "simple arakas patates", ingredients);
        recipeRepository.save(arakas);
        return ResponseEntity.ok("");
    }

    @GetMapping(path = "/test")
    public String works(){
        return "works";
    }
}
