package com.example.FoodShalabackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class FoodShalaBackendApplication {

	public static void main(String[] args) {
		System.out.println("Starting Application");
		SpringApplication.run(FoodShalaBackendApplication.class, args);
	}

}
