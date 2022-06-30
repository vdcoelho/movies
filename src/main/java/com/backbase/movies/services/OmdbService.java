package com.backbase.movies.services;

import com.backbase.movies.configuration.OmdbConfiguration;
import com.backbase.movies.dtos.OmdbResponseDto;
import com.backbase.movies.exceptions.MovieNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

@Service
@AllArgsConstructor
@Slf4j
public class OmdbService {
    private final RestTemplate restTemplate;
    private final OmdbConfiguration omdbConfiguration;

    public OmdbResponseDto findByTitle(String title) {
        log.info("Searching movie with the title '{}' in the omdbAPI", title);
        OmdbResponseDto omdbResponseDto = restTemplate.getForObject(omdbConfiguration.getBaseUrl() + "?t=" + title + "&apikey=" + omdbConfiguration.getApiKey(), OmdbResponseDto.class);
        if (omdbResponseDto.getImdbID() == null) {
            throw new MovieNotFoundException(String.format("Movie not found with title '%s'", title));
        }
        return omdbResponseDto;
    }

    public OmdbResponseDto findByImdbID(String imdbID) {
        log.info("Searching movie with the imdbID '{}' in the omdbAPI", imdbID);
        OmdbResponseDto omdbResponseDto = restTemplate.getForObject(omdbConfiguration.getBaseUrl() + "?i=" + imdbID + "&apikey=" + omdbConfiguration.getApiKey(), OmdbResponseDto.class);
        if (omdbResponseDto.getImdbID() == null) {
            throw new MovieNotFoundException(String.format("Movie not found with imdbID '%s'", imdbID));
        }
        return omdbResponseDto;
    }

    public BigDecimal toBigDecimal(String value) {
        if (value == null) return null;
        if ("N/A".equals(value)) return null;

        try {
            return new BigDecimal(value.replaceAll("(?<=\\d),(?=\\d)|\\$", ""));
        } catch (Exception e) {
            return null;
        }
    }

}
