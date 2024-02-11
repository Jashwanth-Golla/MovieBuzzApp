package com.moviebuzz.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moviebuzz.entity.Movie;
import com.moviebuzz.exception.NullAttributeException;
import com.moviebuzz.service.MovieBuzzService;

import jakarta.validation.Valid;



@RestController
@RequestMapping("/moviebuzz/movies")
public class MovieBuzzController {
	@Autowired
	private MovieBuzzService movieBuzzService;

	Map<String, String> errorMap = new HashMap<>();
	JSONObject jsonObject;
	
	
	//Get a movie by ID
	@GetMapping("/{id}")
	public ResponseEntity<Object> getMovieById(@PathVariable Long id) {
		return movieBuzzService.getMovieById(id);
	}
	
	//Get all movies
	@GetMapping
	public ResponseEntity<List<Movie>> getAllMovies() {
		return movieBuzzService.getAllMovies();
	}
	
	@PostMapping
	public ResponseEntity<Object> publishMovie(@Valid @RequestBody Movie movie,BindingResult bindingResult) {
		jsonObject = new JSONObject();
		if(bindingResult.hasErrors()) {
			errorMap.put("CreationFailed", "Missing parameters");
			errorMap.put("BadRequest","Null or Empty Parameters found");
			if(movie.getMovieGenre()== null | movie.getMovieRating() == null | movie.getMovieName() == null | movie.getMovieReview() == null) {
				jsonObject.put("CreationFailed", errorMap.get("CreationFailed"));
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(jsonObject);
			}
		}
		if (movie.getMovieGenre() == null || movie.getMovieRating() == null ||
		        movie.getMovieName() == null || movie.getMovieReview() == null ||
		        movie.getMovieGenre() == null || movie.getMovieRating() == null ||
		        movie.getMovieName().trim().isEmpty() || movie.getMovieReview().trim().isEmpty()) {
				jsonObject.put("BadRequest", errorMap.get("BadRequest"));
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(jsonObject);
		    }
		return movieBuzzService.publishMovie(movie);
	}
	
	@PutMapping()
	public ResponseEntity<Object> updateMovie(@Valid @RequestBody Movie movie,BindingResult bindingResult) {
		jsonObject = new JSONObject();	
		errorMap.put("UpdationFailed", "Missing parameters");
		errorMap.put("BadRequest","Null or Empty Parameters found");
		if(bindingResult.hasErrors()) {
			if(movie.getMovieGenre()== null || movie.getMovieRating() == null || movie.getMovieName() == null || movie.getMovieReview() == null) {
				jsonObject.put("UpdationFailed", errorMap.get("UpdationFailed"));
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(jsonObject);
			}
		}
		if (movie.getMovieGenre() == null || movie.getMovieRating() == null ||
		        movie.getMovieName() == null || movie.getMovieReview() == null ||
		        movie.getMovieGenre() == null || movie.getMovieRating() == null ||
		        movie.getMovieName().trim().isEmpty() || movie.getMovieReview().trim().isEmpty()) {
				jsonObject.put("BadRequest", errorMap.get("BadRequest"));
		        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(jsonObject);
		    }
		return movieBuzzService.updateMovie(movie);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteMovie(@PathVariable Long id) {
		return movieBuzzService.deleteMovie(id);
	}  

}
