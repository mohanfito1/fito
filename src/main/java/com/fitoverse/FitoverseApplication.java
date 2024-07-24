package com.fitoverse;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FitoverseApplication {

	public static void main(String[] args) {
		SpringApplication.run(FitoverseApplication.class, args);
	}

	@PostConstruct
	public void logPort() {
		System.out.println("Application is running on port: " + System.getenv("port"));
	}
}
