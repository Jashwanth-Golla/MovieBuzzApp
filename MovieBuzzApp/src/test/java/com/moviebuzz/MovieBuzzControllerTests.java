package com.moviebuzz;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.moviebuzz.entity.Genre;
import com.moviebuzz.entity.Movie;

@WebMvcTest(com.moviebuzz.controller.MovieBuzzController.class)
public class MovieBuzzControllerTests {

	    @Autowired
	    private ObjectMapper objectMapper;
	    
	    @Autowired
	    private MockMvc mockMvc;


	    @Test
	    public void testCreateMovie() throws Exception {
	        String movieName = "Barbie";
	        Genre movieGenre = Genre.ACTION;
	        Double movieRating = 4.5;
	        String movieReview = "Great movie!";

	        Movie movie = new Movie(movieName, movieGenre, movieRating, movieReview);

	        mockMvc.perform(post("/moviebuzz/movies")
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(objectMapper.writeValueAsString(movie)))
	                .andExpect(status().isCreated());

	    }
}

