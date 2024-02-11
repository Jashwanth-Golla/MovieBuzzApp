package com.moviebuzz;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.moviebuzz.controller.MovieBuzzController;
import com.moviebuzz.entity.Genre;
import com.moviebuzz.entity.Movie;
import com.moviebuzz.service.MovieBuzzService;

@WebMvcTest(MovieBuzzController.class)
public class MovieControllerTest {

    @Autowired
    private MockMvc mockMvc;
    
    @Autowired
    ObjectMapper mapper;

    @MockBean
    private MovieBuzzService movieBuzzService;

    @InjectMocks
    private MovieBuzzController movieBuzzController;
    
    // Mocking the service response
    Movie movie1 = new Movie(1l,"Hi Nanna",Genre.DRAMA,4.9,"Most Emotional Film of 2023");
    Movie movie2 = new Movie(2l,"Joe",Genre.ROMCOM,4.3,"Fun filled with emotions.Decent movie");
    List<Movie> movies = Arrays.asList(movie1, movie2);
    
    //Test get all movies
    @Test
    public void testGetAllMoviesForSuccess() throws Exception {
        when(movieBuzzService.getAllMovies()).thenReturn(new ResponseEntity<>(movies, HttpStatus.OK));

        // Performing the GET request
        mockMvc.perform(MockMvcRequestBuilders.get("/moviebuzz/movies")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].movieId").value(1)) // Assuming the response JSON structure contains id, name, and genre fields
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].movieName").value("Hi Nanna"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].movieGenre").value("DRAMA"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].movieId").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].movieName").value("Joe"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].movieGenre").value("ROMCOM"));
    }
    
    //Test get a movie by ID
    @Test
    public void testGetMovieByIdForSuccess() throws Exception {
        // Convert Movie object to JSON string
        ObjectMapper objectMapper = new ObjectMapper();
        String movieJson = objectMapper.writeValueAsString(movie1);

        // Create ResponseEntity<String> object
        ResponseEntity<Object> responseEntity = new ResponseEntity<>(movieJson, HttpStatus.FOUND);

        // Stub the service method to return the ResponseEntity<String> object
        when(movieBuzzService.getMovieById(1L)).thenReturn(responseEntity);

        // Performing the GET request
        mockMvc.perform(MockMvcRequestBuilders.get("/moviebuzz/movies/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.movieId").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.movieName").value("Hi Nanna"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.movieGenre").value("DRAMA"));
    }
    
    //Test Post Movie
    @Test
    public void testPublishMovieForSuccess() throws Exception {
    	ObjectMapper objectMapper = new ObjectMapper();
        String movieJson = objectMapper.writeValueAsString(movie1);
        Mockito.when(movieBuzzService.publishMovie(movie1)).thenReturn(new ResponseEntity<>(movieJson, HttpStatus.CREATED));

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/moviebuzz/movies")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(movie1));

        mockMvc.perform(mockRequest)
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.movieName", is("Hi Nanna")));
        }
    
    //Test Put/ Update movie
    @Test
    public void testUpdateMovieForSuccess() throws Exception {
        Movie updatedMovie = new Movie(1l,"Hi Nanna",Genre.DRAMA,5.0,"The Best Movie");
        ObjectMapper objectMapper = new ObjectMapper();
        String oldMovieJson = objectMapper.writeValueAsString(movie1);
        String updatedMovieJson = objectMapper.writeValueAsString(updatedMovie);
        Mockito.when(movieBuzzService.getMovieById(movie1.getMovieId())).thenReturn(new ResponseEntity<>(oldMovieJson, HttpStatus.FOUND));
        Mockito.when(movieBuzzService.updateMovie(updatedMovie)).thenReturn(new ResponseEntity<>(updatedMovieJson, HttpStatus.OK));

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.put("/moviebuzz/movies")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(updatedMovie));

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.movieName", is("Hi Nanna")));
    }

}
