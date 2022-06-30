package com.backbase.movies.controllers;

import com.backbase.movies.dtos.RatingRequestDto;
import com.backbase.movies.dtos.RatingResponseDto;
import com.backbase.movies.models.Rating;
import com.backbase.movies.services.RatingService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/ratings")
@AllArgsConstructor
public class RatingController {
    private final RatingService ratingService;

    @PostMapping
    public Rating rate(@Valid @RequestBody RatingRequestDto ratingRequestDto) {
        return ratingService.rate(ratingRequestDto);
    }

    @GetMapping("top10-rated")
    public List<RatingResponseDto> getTop10RatedByBoxOffice() {
        return ratingService.getTop10RatedByBoxOffice();
    }


}
