package com.backbase.movies.dtos;

import lombok.*;

import java.math.BigDecimal;
import java.util.Comparator;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class RatingResponseDto {
    private String imdbID;
    private String title;
    private Long votes;
    private Double rating;
    private BigDecimal boxOffice;

    public RatingResponseDto(String imdbID, Double rating, Long votes) {
        this.imdbID = imdbID;
        this.votes = votes;
        this.rating = rating;
    }


}
