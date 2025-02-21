package com.internintelligence.movieapi.service.impl;

import com.internintelligence.movieapi.dto.MovieDTO;
import com.internintelligence.movieapi.exception.ResourceNotFoundException;
import com.internintelligence.movieapi.mapper.MovieMapper;
import com.internintelligence.movieapi.model.Movie;
import com.internintelligence.movieapi.repository.MovieRepository;
import com.internintelligence.movieapi.service.MovieService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;

    @Override
    public Movie createMovie(MovieDTO movieDTO) {
        log.info("Creating a new movie: {}", movieDTO.getTitle());
        Movie movie = MovieMapper.toEntity(movieDTO);
        return movieRepository.save(movie);
    }

    @Override
    public List<Movie> getAllMovies() {
        log.info("Fetching all movies from the database");
        return movieRepository.findAll();
    }

    @Override
    public Movie getMovieById(String id) {
        log.info("Fetching movie with ID: {}", id);
        return movieRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Movie not found with ID: {}", id);
                    return new ResourceNotFoundException("Movie not found with ID: " + id);
                });
    }

    @Override
    public Movie updateMovie(String id, MovieDTO movieDTO) {
        log.info("Updating movie with ID: {}", id);
        Movie existingMovie = movieRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Movie not found with ID: " + id));

        existingMovie.setTitle(movieDTO.getTitle());
        existingMovie.setDirector(movieDTO.getDirector());
        existingMovie.setReleaseYear(movieDTO.getReleaseYear());
        existingMovie.setGenre(movieDTO.getGenre());
        existingMovie.setImdbRating(movieDTO.getImdbRating());

        log.info("Updated movie details: {}", existingMovie);
        return movieRepository.save(existingMovie);
    }

    @Override
    public void deleteMovie(String id) {
        log.info("Deleting movie with ID: {}", id);
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Movie not found with ID: " + id));
        movieRepository.delete(movie);
        log.info("Movie with ID: {} has been deleted", id);
    }

    @Override
    public List<Movie> getMoviesByGenre(String genre) {
        log.info("Fetching movies by genre: {}", genre);
        return movieRepository.findByGenre(genre, Pageable.unpaged()).getContent();
    }

    @Override
    public Page<Movie> getMoviesByGenre(String genre, Pageable pageable) {
        log.info("Fetching paginated movies by genre: {}", genre);
        return movieRepository.findByGenre(genre, pageable);
    }

    @Override
    public List<Movie> searchMoviesByDirector(String director) {
        log.info("Searching movies by director: {}", director);
        return movieRepository.findByDirectorContainingIgnoreCase(director, Pageable.unpaged()).getContent();
    }

}
