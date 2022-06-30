package com.backbase.movies.controllers;

import com.backbase.movies.dtos.OmdbResponseDto;
import com.backbase.movies.services.MovieService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/movies")
@AllArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @GetMapping("by-title")
    public OmdbResponseDto findByTitle(@RequestParam String title) {
        return movieService.findByTitle(title);
    }

}
