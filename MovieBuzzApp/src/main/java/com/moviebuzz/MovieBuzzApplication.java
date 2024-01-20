package com.moviebuzz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication(scanBasePackages = "com.moviebuzz")
@PropertySource("application.properties")
public class MovieBuzzApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovieBuzzApplication.class, args);
	}

}
