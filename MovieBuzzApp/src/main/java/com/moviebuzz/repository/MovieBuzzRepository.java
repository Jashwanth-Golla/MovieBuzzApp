package com.moviebuzz.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.moviebuzz.entity.Movie;

@Repository
public interface MovieBuzzRepository extends CrudRepository<Movie, Long>{
	List<Movie> findAll();
}
