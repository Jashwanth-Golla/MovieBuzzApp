package com.moviebuzz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
//@PropertySource("application.properties") // For letting the Spring Boot know the properties file 
public class MovieBuzzApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovieBuzzApplication.class, args);
	}

}
