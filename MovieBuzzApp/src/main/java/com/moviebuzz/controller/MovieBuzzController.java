package com.moviebuzz.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.moviebuzz.entity.Movie;
import com.moviebuzz.service.MovieBuzzService;

@RestController
@RequestMapping("/moviebuzz/movies")
public class MovieBuzzController {
	//Swagger UI - url - swagger-ui/index.html
	@Autowired
	private MovieBuzzService movieBuzzService;
	
	//get a movie by ID
	@GetMapping("/{id}")
	public Movie getMovieById(@PathVariable Long id) {
		return movieBuzzService.getMovieById(id);
	}
	
	@GetMapping
	public List<Movie> getAllMovies(){
		return movieBuzzService.getAllMovies();
	}
	
	@PostMapping
	public Movie publishMovie(Movie movie) {
		return movieBuzzService.publishMovie(movie);
	}
	
	@PutMapping
	public Movie updateMovie(Movie movie) {
		return movieBuzzService.updateMovie(movie);
	}
	
	@DeleteMapping
	public boolean deleteMovie(Long id) {
		return movieBuzzService.deleteMovie(id);
	}

}
