package com.backbase.movies.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Comparator;

@Data
@AllArgsConstructor
@NoArgsConstructor
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

    public static Comparator<RatingResponseDto> boxOfficeComparator = (o1, o2) -> {
        final boolean f1, f2;
        return (f2 = o2.getBoxOffice() == null) ^ (f1 = o1.getBoxOffice() == null) ? f2 ? -1 : 1 : f2 && f1 ? 0 : o2.getBoxOffice().compareTo(o1.getBoxOffice());
    };
}
