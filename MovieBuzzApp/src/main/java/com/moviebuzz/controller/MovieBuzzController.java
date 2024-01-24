package com.moviebuzz.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moviebuzz.entity.Movie;
import com.moviebuzz.exception.NullAttributeException;
import com.moviebuzz.exception.NotFoundException;
import com.moviebuzz.service.MovieBuzzService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/moviebuzz/movies")
public class MovieBuzzController {
	@Autowired
	private MovieBuzzService movieBuzzService;
	
	//Get a movie by ID
	@GetMapping("/{id}")
	public ResponseEntity<String> getMovieById(@PathVariable Long id) {
		return movieBuzzService.getMovieById(id);
	}
	
	//Get all movies
	@GetMapping
	public ResponseEntity<List<Movie>> getAllMovies() {
		return movieBuzzService.getAllMovies();
	}
	
	@PostMapping
	public ResponseEntity<String> publishMovie(@Valid @RequestBody Movie movie,BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			if(movie.getMovieGenre()== null | movie.getMovieRating() == null | movie.getMovieName() == null | movie.getMovieReview() == null) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Creation Failed : You missed few parameters");
			}
		}
		if (movie.getMovieGenre() == null || movie.getMovieRating() == null ||
		        movie.getMovieName() == null || movie.getMovieReview() == null ||
		        movie.getMovieGenre() == null || movie.getMovieRating() == null ||
		        movie.getMovieName().trim().isEmpty() || movie.getMovieReview().trim().isEmpty()) {
		        throw new NullAttributeException("Bad Request : Have you given any attribute a Null value?");
		    }
		return movieBuzzService.publishMovie(movie);
	}
	
	@PutMapping()
	public ResponseEntity<String> updateMovie(@Valid @RequestBody Movie movie,BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			if(movie.getMovieGenre()== null | movie.getMovieRating() == null | movie.getMovieName() == null | movie.getMovieReview() == null) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Updation Failed : You missed few parameters");
			}
		}
		if (movie.getMovieGenre() == null || movie.getMovieRating() == null ||
		        movie.getMovieName() == null || movie.getMovieReview() == null ||
		        movie.getMovieGenre() == null || movie.getMovieRating() == null ||
		        movie.getMovieName().trim().isEmpty() || movie.getMovieReview().trim().isEmpty()) {
		        throw new NullAttributeException("Bad Request : Have you given any attribute a Null value?");
		    }
		return movieBuzzService.updateMovie(movie);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteMovie(@PathVariable Long id) {
		return movieBuzzService.deleteMovie(id);
	}  

}
