package com.backbase.movies.services;

import com.backbase.movies.dtos.OmdbResponseDto;
import com.backbase.movies.dtos.RatingRequestDto;
import com.backbase.movies.dtos.RatingResponseDto;
import com.backbase.movies.models.Rating;
import com.backbase.movies.repositories.RatingRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import static com.backbase.movies.dtos.RatingResponseDto.boxOfficeComparator;

@Service
@AllArgsConstructor
@Slf4j
public class RatingService {
    private final RatingRepository ratingRepository;
    private final OmdbService omdbService;

    @Transactional
    public Rating rate(RatingRequestDto ratingRequestDto) {
        OmdbResponseDto omdbResponseDto = omdbService.findByTitle(ratingRequestDto.getTitle());

        Rating rating = Rating.builder()
                .rating(new BigDecimal(ratingRequestDto.getRate()))
                .imdbID(omdbResponseDto.getImdbID())
                .build();
        log.info("Saving the rate {} for the movie {}", ratingRequestDto.getRate(), omdbResponseDto.getTitle());
        return ratingRepository.save(rating);
    }

    public List<RatingResponseDto> getTop10RatedByBoxOffice() {
        List<RatingResponseDto> ratingResponseDtos = ratingRepository.listTopRated(Pageable.ofSize(10));

        ratingResponseDtos.forEach(ratingResponseDto -> {
            OmdbResponseDto omdbResponseDto = omdbService.findByImdbID(ratingResponseDto.getImdbID());
            ratingResponseDto.setTitle(omdbResponseDto.getTitle());
            ratingResponseDto.setBoxOffice(omdbService.toBigDecimal(omdbResponseDto.getBoxOffice()));
        });

        return ratingResponseDtos.stream().sorted(boxOfficeComparator).collect(Collectors.toList());

    }



}
