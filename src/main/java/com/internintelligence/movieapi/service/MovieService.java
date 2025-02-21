package com.internintelligence.movieapi.service;

import com.internintelligence.movieapi.dto.MovieDTO;
import com.internintelligence.movieapi.model.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MovieService {
    Movie createMovie(MovieDTO movieDTO);
    Movie updateMovie(String id, MovieDTO movieDTO);
    void deleteMovie(String id);
    Movie getMovieById(String id);
    List<Movie> getAllMovies();
    List<Movie> getMoviesByGenre(String genre);
    Page<Movie> getMoviesByGenre(String genre, Pageable pageable);
    List<Movie> searchMoviesByDirector(String director);
}
