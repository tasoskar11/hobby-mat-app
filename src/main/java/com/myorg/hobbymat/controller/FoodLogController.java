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
import com.myorg.hobbymat.model.Recipe;
import com.myorg.hobbymat.repository.FoodLogRepository;
import com.myorg.hobbymat.scenario5.BacklogCreatedResponse;

import java.lang.invoke.MethodHandles;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

@RestController
@RequestMapping("/api/foodLogs")
public class FoodLogController {
    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    @Value("${myorg.jms.artemis.queue.notifications:customer.queue.contact.notifications}")
    private String publisherQueue;

    @Autowired
    JmsTemplate publisherJmsTemplate;

    @Autowired
    FoodLogRepository foodLogRepository;

    @GetMapping
    public List<FoodLog> getFoodLogs() {
        return foodLogRepository.findAll();
    }



    @PostMapping
    public ResponseEntity createFoodLog(FoodLog entry) throws URISyntaxException {
        FoodLog saved = foodLogRepository.save(entry);
        logger.info("Request was received and it was added to the backlog");
        return ResponseEntity.created(new URI("/foodLogs/" + saved.getId())).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateFoodLog(@PathVariable String id, @RequestBody FoodLog foodLog) {
        FoodLog current = foodLogRepository.findById(id).orElseThrow(RuntimeException::new);
        logger.info("Update request was received with body:{}", foodLog);
        logger.info("Replacing current food log entry with body:{}", current);
        //TODO this is a temporary approach during development. The goal is to have a validator.
        if (foodLog.getRecipe() != null) {
            current.setRecipe(foodLog.getRecipe());
        }
        current.setDate(foodLog.getDate());
        current = foodLogRepository.save(current);

        return ResponseEntity.ok(current);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteFoodLog(@PathVariable String id) {
        foodLogRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<FoodLog> getFoodLog(@PathVariable String id) {
        FoodLog current = foodLogRepository.findById(id).orElseThrow(RuntimeException::new);
        return ResponseEntity.ok(current);
    }

    @PostMapping(path = "/initialize", produces = MediaType.APPLICATION_JSON_VALUE)
    public BacklogCreatedResponse initializeWithDummyLogs() {
        
        LocalDateTime firstDate = LocalDateTime.of(2023, Month.JANUARY, 22, 12, 00);
        Recipe firstRecipe = new Recipe("grekisk bifteki med potatis i ugnen", null, "some description", null);
        
        

        FoodLog first = new FoodLog(firstDate, firstRecipe);

        Recipe secondRecipe = new Recipe("ärtor gryta by akis", null, "some description", null);
        LocalDateTime secondDate = LocalDateTime.of(2023, Month.JANUARY, 23, 12, 00);
        FoodLog second = new FoodLog(secondDate, secondRecipe);

        Recipe thirdRecipe = new Recipe("spaghetti med veg sås", null, "some description", null);
        LocalDateTime thirdDate = LocalDateTime.of(2023, Month.JANUARY, 24, 12, 00);
        FoodLog third = new FoodLog(thirdDate, thirdRecipe);

        Recipe fourthRecipe = new Recipe("guldsparid från medelhavet", null, "some description", null);
        LocalDateTime fourthDate = LocalDateTime.of(2023, Month.JANUARY, 25, 18, 00);
        FoodLog fourth = new FoodLog(fourthDate, fourthRecipe);

        Recipe fifthRecipe = new Recipe("kycklingben tomatsås spaghetti", null, "some description", null);

        LocalDateTime fifthDate = LocalDateTime.of(2023, Month.JANUARY, 26, 12, 00);
        FoodLog fifth = new FoodLog(fifthDate, fifthRecipe);

        Recipe sixthRecipe = new Recipe("rödspätta", null, "some description", null);

        LocalDateTime sixthDate = LocalDateTime.of(2023, Month.JANUARY, 28, 12, 00);
        FoodLog sixth = new FoodLog(sixthDate, sixthRecipe);

        foodLogRepository.insert(first);
        foodLogRepository.insert(second);
        foodLogRepository.insert(third);
        foodLogRepository.insert(fourth);
        foodLogRepository.insert(fifth);
        foodLogRepository.insert(sixth);
        BacklogCreatedResponse createdResponse = new BacklogCreatedResponse();
        createdResponse.setCreated("yes it was added to the backlog");
        createdResponse.setId(first.getId());
        logger.info("Request was received and it was added to the backlog");
        return createdResponse;
    }

    @GetMapping(path = "/test")
    public String works(){
        return "works";
    }
}
