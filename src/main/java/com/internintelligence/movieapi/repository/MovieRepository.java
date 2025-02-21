package com.internintelligence.movieapi.repository;

import com.internintelligence.movieapi.model.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MovieRepository extends MongoRepository<Movie, String> {
    Page<Movie> findByGenre(String genre, Pageable pageable);
    Page<Movie> findByDirectorContainingIgnoreCase(String director, Pageable pageable); // Yeni metod
}
