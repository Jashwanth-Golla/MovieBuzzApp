package com.moviebuzz.entity;


import java.util.Optional;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Movie {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="movie_id")
	private Long movieId;
	
	@NotNull
	@Column(name="movie_name")
	@Size(min=2,message = "Movie name should have atleast 2 characters")
	private String movieName;
	
	@NotNull
	@Column(name="movie_genre")
	@Enumerated(EnumType.STRING)
	private Genre movieGenre;
	
	@NotNull
	@Column(name="movie_rating")
	@DecimalMin(value ="0.0",inclusive = true)
	@DecimalMax(value="5.0",inclusive=true)
	private Double movieRating;
	
	@NotNull
	@Column(name="movie_review")
	@Size(min=3,message = "Movie name should have atleast 2 characters")
	private String movieReview;

	@Override
	public String toString() {
		return "Movie Id: " + movieId + "\nMovie Name: " + movieName + "\nMovie Genre: " + movieGenre
				+ "\nMovie Rating: " + movieRating + "\nMovie Review: " + movieReview;
	}
	
	public <T> String valueToString(T value, String label) {
		return Optional.ofNullable(value) .map(v -> label + ": " + v.toString()) .orElse(""); 
		}

	public Movie(String movieName, Genre movieGenre, Double movieRating, String movieReview) {
		this.movieName = movieName;
		this.movieGenre = movieGenre;
		this.movieRating = movieRating;
		this.movieReview = movieReview;
	}

	
}
