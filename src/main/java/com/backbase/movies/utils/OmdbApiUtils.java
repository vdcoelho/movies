package com.backbase.movies.utils;

import com.backbase.movies.dtos.RatingResponseDto;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Comparator;

@Component
public class OmdbApiUtils {

    public BigDecimal stringToBigDecimal(String value) {
        if (value == null) return null;
        if ("N/A".equals(value)) return null;

        try {
            return new BigDecimal(value.replaceAll("(?<=\\d),(?=\\d)|\\$", ""));
        } catch (Exception e) {
            return null;
        }
    }

    public static Comparator<RatingResponseDto> boxOfficeComparatorInverted = (o1, o2) -> {
        final boolean f1, f2;
        return (f2 = o2.getBoxOffice() == null) ^ (f1 = o1.getBoxOffice() == null) ? f2 ? -1 : 1 : f2 && f1 ? 0 : o2.getBoxOffice().compareTo(o1.getBoxOffice());
    };

    public static Comparator<RatingResponseDto> ratingComparatorInverted = (o1, o2) -> {
        final boolean f1, f2;
        return (f2 = o2.getRating() == null) ^ (f1 = o1.getRating() == null) ? f2 ? -1 : 1 : f2 && f1 ? 0 : o2.getRating().compareTo(o1.getRating());
    };
}
