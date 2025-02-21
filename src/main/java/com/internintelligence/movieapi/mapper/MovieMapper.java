package com.internintelligence.movieapi.mapper;

import com.internintelligence.movieapi.dto.MovieDTO;
import com.internintelligence.movieapi.model.Movie;

public class MovieMapper {

    public static Movie toEntity(MovieDTO dto) {
        return Movie.builder()
                .title(dto.getTitle())
                .director(dto.getDirector())
                .releaseYear(dto.getReleaseYear())
                .genre(dto.getGenre())
                .imdbRating(dto.getImdbRating())
                .build();
    }

    public static MovieDTO toDTO(Movie movie) {
        return MovieDTO.builder()
                .title(movie.getTitle())
                .director(movie.getDirector())
                .releaseYear(movie.getReleaseYear())
                .genre(movie.getGenre())
                .imdbRating(movie.getImdbRating())
                .build();
    }
}
