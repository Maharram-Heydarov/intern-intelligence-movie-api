package com.internintelligence.movieapi.dto;

import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MovieDTO {

    @NotBlank(message = "Title is mandatory")
    private String title;

    @NotBlank(message = "Director is mandatory")
    private String director;

    @Min(value = 1888, message = "Release year cannot be earlier than 1888")
    @Max(value = 2100, message = "Release year should be valid")
    private int releaseYear;

    @NotBlank(message = "Genre must be provided")
    private String genre;

    @DecimalMin(value = "0.0", message = "Rating must be at least 0.0")
    @DecimalMax(value = "10.0", message = "Rating cannot exceed 10.0")
    private double imdbRating;
}
