package com.internintelligence.movieapi.controller;

import com.internintelligence.movieapi.dto.MovieDTO;
import com.internintelligence.movieapi.model.Movie;
import com.internintelligence.movieapi.service.MovieService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

@RestController
@RequestMapping("/api/v1/movies")
@Tag(name = "Movie API", description = "CRUD operations for movies")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @PostMapping
    @Operation(summary = "Add a new movie")
    public ResponseEntity<Movie> addMovie(@Valid @RequestBody MovieDTO movieDTO) {
        return new ResponseEntity<>(movieService.createMovie(movieDTO), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get movie by ID")
    public ResponseEntity<Movie> getMovieById(@PathVariable String id) {
        return ResponseEntity.ok(movieService.getMovieById(id));
    }

    @GetMapping
    @Operation(summary = "Get all movies")
    public ResponseEntity<List<Movie>> getAllMovies() {
        return ResponseEntity.ok(movieService.getAllMovies());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a movie by ID")
    public ResponseEntity<Movie> updateMovie(@PathVariable String id, @Valid @RequestBody MovieDTO movieDTO) {
        return ResponseEntity.ok(movieService.updateMovie(id, movieDTO));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a movie by ID")
    public ResponseEntity<Void> deleteMovie(@PathVariable String id) {
        movieService.deleteMovie(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/genre/{genre}")
    @Operation(summary = "Get movies by genre")
    public ResponseEntity<List<Movie>> getMoviesByGenre(@PathVariable String genre) {
        return ResponseEntity.ok(movieService.getMoviesByGenre(genre));
    }

    @GetMapping("/genre/{genre}/paged")
    @Operation(summary = "Get paginated movies by genre")
    public ResponseEntity<List<Movie>> getPagedMoviesByGenre(
            @PathVariable String genre,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(movieService.getMoviesByGenre(genre, pageable).getContent());
    }

    @GetMapping("/director/{director}")
    @Operation(summary = "Search movies by director")
    public ResponseEntity<List<Movie>> searchMoviesByDirector(@PathVariable String director) {
        return ResponseEntity.ok(movieService.searchMoviesByDirector(director));
    }
}
