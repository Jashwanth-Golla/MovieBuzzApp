package com.moviebuzz.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.moviebuzz.entity.Movie;
import com.moviebuzz.exception.NullAttributeException;
import com.moviebuzz.exception.NotFoundException;
import com.moviebuzz.repository.MovieBuzzRepository;

@Service
public class MovieBuzzService {
	
	@Autowired
	private MovieBuzzRepository movieBuzzRepo;
	
	//Get Mapping - By Id
	public ResponseEntity<String> getMovieById(Long id) {
		Optional<Movie> movieCheck = movieBuzzRepo.findById(id);
		if(movieCheck.isPresent()) {
			Movie movie = movieBuzzRepo.findById(id).get();
			return ResponseEntity.status(HttpStatus.FOUND).body("Movie Fetched Successfully!!\n----------------------------\n" + movie.toString());
		}
		else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Oops! We could not find the movie requested");
		}
	}
	
	// Get Mapping - All
	public ResponseEntity<List<Movie>> getAllMovies() {
		List<Movie> movies = movieBuzzRepo.findAll();
		return ResponseEntity.status(HttpStatus.FOUND).body(movies);
	}
	
	//Post Mapping
	public ResponseEntity<String> publishMovie(@RequestBody Movie movie) {
		Movie body = movieBuzzRepo.save(movie);
		if(movie.getMovieGenre()== null | movie.getMovieRating() == null | movie.getMovieName() == null | movie.getMovieReview()== null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Operation Failed : You missed few parameters");
		}
		return ResponseEntity.status(HttpStatus.CREATED).body("Movie Created Successfully!!\n----------------------------\n" + body.toString());
	}
	
	//Put Mapping
	public ResponseEntity<String> updateMovie(Movie movie) {
		Movie body = movieBuzzRepo.save(movie);
		if(movie.getMovieGenre()== null | movie.getMovieRating() == null | movie.getMovieName() == null | movie.getMovieReview()== null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Operation Failed : You missed few parameters");
		}
		return ResponseEntity.status(HttpStatus.OK).body("Movie Updated Successfully!!\n----------------------------\n" + body.toString());
	}
	
	//Delete Mapping
	public ResponseEntity<String> deleteMovie(Long id) {
		Optional<Movie> movie = movieBuzzRepo.findById(id);
		if(movie.isPresent()) {
			movieBuzzRepo.deleteById(id);
			return ResponseEntity.status(HttpStatus.OK).body("Movie Deleted Successfully!!\nNo Content to return and display.");
		}
		else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Oops!!We could not found the movie you requested.");
		}
		
	}
	
}
