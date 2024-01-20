package com.moviebuzz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moviebuzz.entity.Movie;
import com.moviebuzz.repository.MovieBuzzRepository;

@Service
public class MovieBuzzService {
	
	@Autowired
	private MovieBuzzRepository movieBuzzRepo;
	
	public Movie getMovieById(Long id) {
		return movieBuzzRepo.findById(id).get();
	}
	
	public List<Movie> getAllMovies(){
		return movieBuzzRepo.findAll();
	}
	
	public Movie publishMovie(Movie movie) {
		return movieBuzzRepo.save(movie);
	}
	
	public Movie updateMovie(Movie movie) {
		return movieBuzzRepo.save(movie);
	}
	
	public boolean deleteMovie(Long id) {
		movieBuzzRepo.deleteById(id);
		return true;
	}
}
