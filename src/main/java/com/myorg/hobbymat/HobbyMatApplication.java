package com.myorg.hobbymat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;



@EnableMongoRepositories
@SpringBootApplication
public class HobbyMatApplication {

	public static void main(String[] args) {
		SpringApplication.run(HobbyMatApplication.class, args);
	}

}
