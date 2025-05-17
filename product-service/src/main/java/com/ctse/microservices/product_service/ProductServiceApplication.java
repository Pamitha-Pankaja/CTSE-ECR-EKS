package com.ctse.microservices.product_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Uses @SpringBootApplication to enable auto-configuration and component scanning.
@SpringBootApplication // Combines @Configuration, @EnableAutoConfiguration, and @ComponentScan
public class ProductServiceApplication {

	public static void main(String[] args) {
        // Bootstraps the Spring Boot application
		SpringApplication.run(ProductServiceApplication.class, args);
	}

}
