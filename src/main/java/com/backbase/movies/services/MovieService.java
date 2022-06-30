package com.backbase.movies.services;

import com.backbase.movies.dtos.OmdbResponseDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class MovieService {
    private final OmdbService omdbService;

    public OmdbResponseDto findByTitle(String title) {
        return omdbService.findByTitle(title);
    }

}
