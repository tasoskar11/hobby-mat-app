package com.myorg.hobbymat.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.myorg.hobbymat.model.FoodLog;
import com.myorg.hobbymat.model.Ingredient;
import com.myorg.hobbymat.model.Recipe;
import com.myorg.hobbymat.repository.FoodLogRepository;
import com.myorg.hobbymat.repository.IngredientRepository;
import com.myorg.hobbymat.scenario5.BacklogCreatedResponse;

import java.lang.invoke.MethodHandles;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

@RestController
@RequestMapping("/api/ingredients")
public class IngredientController {
    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());


    @Autowired
    IngredientRepository ingredientRepository;

    @GetMapping
    public List<Ingredient> getIngredients() {
        return ingredientRepository.findAll();
    }



    @PostMapping
    public ResponseEntity<Ingredient> register(@RequestBody Ingredient entry) throws URISyntaxException {
        Ingredient saved = ingredientRepository.save(entry);
        return ResponseEntity.created(new URI("/foodLogs/" + saved.getId())).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ingredient> updateIngredient(@PathVariable String id, @RequestBody Ingredient ingredient) {
        Ingredient current = ingredientRepository.findById(id).orElseThrow(RuntimeException::new);
        current.setName(ingredient.getName());
        current.setImageUrl(ingredient.getImageUrl());
        current = ingredientRepository.save(ingredient);
        return ResponseEntity.ok(current);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Ingredient> deleteIngredient(@PathVariable String id) {
        ingredientRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ingredient> getIngredient(@PathVariable String id) {
        Ingredient current = ingredientRepository.findById(id).orElseThrow(RuntimeException::new);
        return ResponseEntity.ok(current);
    }

    @PostMapping(path = "/initialize", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> initializeWithIngredients() {
        
        ingredientRepository.save(new Ingredient("ärtor", null));
        ingredientRepository.save(new Ingredient("potatis", null));
        ingredientRepository.save(new Ingredient("morröter", null));
        ingredientRepository.save(new Ingredient("nöttfärs", null));
        ingredientRepository.save(new Ingredient("kycklingben", null));
        ingredientRepository.save(new Ingredient("halloumi", null));
        ingredientRepository.save(new Ingredient("fläskkarré", null));
        ingredientRepository.save(new Ingredient("högrev", null));
        ingredientRepository.save(new Ingredient("nötgrytbitar", null));
        ingredientRepository.save(new Ingredient("potatis", null));
        ingredientRepository.save(new Ingredient("sötpotatis", null));
        ingredientRepository.save(new Ingredient("lök", null));
        ingredientRepository.save(new Ingredient("vitlök", null));
        ingredientRepository.save(new Ingredient("persilja", null));
        ingredientRepository.save(new Ingredient("dill", null));
        ingredientRepository.save(new Ingredient("oregano", null));
        ingredientRepository.save(new Ingredient("mynta", null));
        ingredientRepository.save(new Ingredient("spaggetti", null));
        ingredientRepository.save(new Ingredient("gröna linser", null));
        ingredientRepository.save(new Ingredient("vita bönor", null));
        ingredientRepository.save(new Ingredient("krossade tomater", null));
        ingredientRepository.save(new Ingredient("havregryn", null));
        ingredientRepository.save(new Ingredient("guldsparid", null));
        ingredientRepository.save(new Ingredient("rödspätta", null));
        ingredientRepository.save(new Ingredient("bulgur", null));
        ingredientRepository.save(new Ingredient("citron", null));
        ingredientRepository.save(new Ingredient("paprika", null));
        ingredientRepository.save(new Ingredient("blomkål", null));
        ingredientRepository.save(new Ingredient("broccoli", null));
        ingredientRepository.save(new Ingredient("svamp", null));
        return ResponseEntity.ok("");
    }

    @GetMapping(path = "/test")
    public String works(){
        return "works";
    }
}
