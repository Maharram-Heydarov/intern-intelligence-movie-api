package com.internintelligence.movieapi.service;

import com.internintelligence.movieapi.dto.MovieDTO;
import com.internintelligence.movieapi.model.Movie;
import com.internintelligence.movieapi.repository.MovieRepository;
import com.internintelligence.movieapi.service.impl.MovieServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class MovieServiceTest {

    @Mock
    private MovieRepository movieRepository;

    @InjectMocks
    private MovieServiceImpl movieService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldCreateMovieSuccessfully() {
        MovieDTO movieDTO = MovieDTO.builder()
                .title("Inception")
                .director("Christopher Nolan")
                .releaseYear(2010)
                .genre("Sci-Fi")
                .imdbRating(8.8)
                .build();

        Movie movie = Movie.builder()
                .title("Inception")
                .director("Christopher Nolan")
                .releaseYear(2010)
                .genre("Sci-Fi")
                .imdbRating(8.8)
                .build();

        when(movieRepository.save(any(Movie.class))).thenReturn(movie);

        Movie createdMovie = movieService.createMovie(movieDTO);

        assertNotNull(createdMovie);
        assertEquals("Inception", createdMovie.getTitle());
        verify(movieRepository, times(1)).save(any(Movie.class));
    }

    @Test
    void shouldFetchMovieById() {
        Movie movie = Movie.builder()
                .id("1")
                .title("Interstellar")
                .director("Christopher Nolan")
                .releaseYear(2014)
                .genre("Sci-Fi")
                .imdbRating(8.6)
                .build();

        when(movieRepository.findById("1")).thenReturn(Optional.of(movie));

        Movie fetchedMovie = movieService.getMovieById("1");

        assertNotNull(fetchedMovie);
        assertEquals("Interstellar", fetchedMovie.getTitle());
        verify(movieRepository, times(1)).findById("1");
    }

    @Test
    void shouldUpdateMovieSuccessfully() {
        Movie existingMovie = Movie.builder()
                .id("1")
                .title("Old Title")
                .director("Old Director")
                .releaseYear(2000)
                .genre("Drama")
                .imdbRating(6.0)
                .build();

        MovieDTO updatedMovieDTO = MovieDTO.builder()
                .title("New Title")
                .director("New Director")
                .releaseYear(2022)
                .genre("Thriller")
                .imdbRating(9.0)
                .build();

        when(movieRepository.findById("1")).thenReturn(Optional.of(existingMovie));
        when(movieRepository.save(any(Movie.class))).thenReturn(existingMovie);

        Movie updatedMovie = movieService.updateMovie("1", updatedMovieDTO);

        assertNotNull(updatedMovie);
        assertEquals("New Title", updatedMovie.getTitle());
        assertEquals("New Director", updatedMovie.getDirector());
        verify(movieRepository, times(1)).save(existingMovie);
    }

    @Test
    void shouldDeleteMovieSuccessfully() {
        Movie movie = Movie.builder()
                .id("1")
                .title("Delete Me")
                .build();

        when(movieRepository.findById("1")).thenReturn(Optional.of(movie));
        doNothing().when(movieRepository).delete(movie);

        movieService.deleteMovie("1");

        verify(movieRepository, times(1)).delete(movie);
    }

    @Test
    void shouldFetchMoviesByGenreWithPagination() {
        Pageable pageable = PageRequest.of(0, 2);
        Movie movie = Movie.builder()
                .title("Sci-Fi Adventure")
                .genre("Sci-Fi")
                .build();

        Page<Movie> moviePage = new PageImpl<>(List.of(movie), pageable, 1);

        when(movieRepository.findByGenre("Sci-Fi", pageable)).thenReturn(moviePage);

        Page<Movie> result = movieService.getMoviesByGenre("Sci-Fi", pageable);

        assertNotNull(result);
        assertEquals(1, result.getContent().size());
        verify(movieRepository, times(1)).findByGenre("Sci-Fi", pageable);
    }

    @Test
    void shouldSearchMoviesByDirector() {
        Movie movie = Movie.builder()
                .title("Director's Cut")
                .director("Famous Director")
                .build();

        Page<Movie> moviePage = new PageImpl<>(Collections.singletonList(movie));

        when(movieRepository.findByDirectorContainingIgnoreCase("Famous Director", Pageable.unpaged()))
                .thenReturn(moviePage);

        List<Movie> result = movieService.searchMoviesByDirector("Famous Director");

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(movieRepository, times(1)).findByDirectorContainingIgnoreCase("Famous Director", Pageable.unpaged());
    }
}
