package com.backbase.movies.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class RatingRequestDto {
    @NotNull(message = "The title is required")
    private String title;
    @Range(min = 1, max = 5, message = "The rating must be between 1 and 5")
    private Integer rate;
}
