package com.internintelligence.movieapi.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.*;

@Document(collection = "movies")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Movie {

    @Id
    private String id;

    @NotBlank(message = "Title cannot be empty")
    private String title;

    @NotBlank(message = "Director name cannot be empty")
    private String director;

    @Min(value = 1888, message = "Release year should not be earlier than 1888")
    @Max(value = 2100, message = "Release year should not be later than 2100")
    private int releaseYear;

    @NotBlank(message = "Genre cannot be empty")
    private String genre;

    @DecimalMin(value = "0.0", message = "Rating cannot be less than 0.0")
    @DecimalMax(value = "10.0", message = "Rating cannot be more than 10.0")
    private double imdbRating;
}
