package com.moviebuzz.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.moviebuzz.entity.Movie;
import com.moviebuzz.repository.MovieBuzzRepository;

@Service
public class MovieBuzzService {
	
	@Autowired
	private MovieBuzzRepository movieBuzzRepo;
	Optional<Movie> movieCheck;
	String errMsg;
	JSONObject jsonObject;
	
	//Get Mapping - By Id
	public ResponseEntity<Object> getMovieById(Long id) {
		Map<String, String> errorResponse = new HashMap<>();
		String errMsg = "Oops! We couldn't find the movie with id " + id.toString();
	    errorResponse.put("error", errMsg);
		movieCheck = movieBuzzRepo.findById(id);
		if(movieCheck.isPresent()) {
			Movie movie = movieBuzzRepo.findById(id).get();
			return ResponseEntity.status(HttpStatus.FOUND).body(movie);
		}
		else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
		}
	}
	
	// Get Mapping - All
	public ResponseEntity<List<Movie>> getAllMovies() {
		List<Movie> movies = movieBuzzRepo.findAll();
		return ResponseEntity.status(HttpStatus.FOUND).body(movies);
	}
	
	//Post Mapping
	public ResponseEntity<Object> publishMovie(@RequestBody Movie movie) {
		Movie body = movieBuzzRepo.save(movie);
		return ResponseEntity.status(HttpStatus.CREATED).body(movie);
	}
	
	//Put Mapping
	public ResponseEntity<Object> updateMovie(Movie movie){
		movieCheck = movieBuzzRepo.findById(movie.getMovieId());
		jsonObject = new JSONObject();
		if (movieCheck.isPresent()) {
			Movie body = movieBuzzRepo.save(movie);
			return ResponseEntity.status(HttpStatus.OK).body(body);
		}else {
			errMsg = "Movie with the id " + movie.getMovieId().toString() + " not found";
			jsonObject.put("UpdateError", errMsg);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(jsonObject);
		}
		
	}
	
	//Delete Mapping
	public ResponseEntity<Object> deleteMovie(Long id) {
		Optional<Movie> movie = movieBuzzRepo.findById(id);
		jsonObject = new JSONObject();
		errMsg = "Movie with the id " + id.toString() + " not found";
		if(movie.isPresent()) {
			movieBuzzRepo.deleteById(id);
			return ResponseEntity.status(HttpStatus.OK).body(movie);
		}
		else {
			jsonObject.put("DeletionError", errMsg);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(jsonObject);
		}
		
	}
	
}
