package com.backbase.movies.services;

import com.backbase.movies.configuration.OmdbConfiguration;
import com.backbase.movies.dtos.OmdbResponseDto;
import com.backbase.movies.exceptions.MovieNotFoundException;
import com.backbase.movies.exceptions.OmdbApiException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
@AllArgsConstructor
@Slf4j
public class OmdbService {
    private final RestTemplate restTemplate;
    private final OmdbConfiguration omdbConfiguration;
    private static Map<String, OmdbResponseDto> CACHE = new HashMap<>();

    @CircuitBreaker(name = "omdbApiCB", fallbackMethod = "outOfService")
    public OmdbResponseDto findByTitle(String title) {
        log.info("Searching movie with the title '{}' in the omdbAPI", title);
        OmdbResponseDto omdbResponseDto = restTemplate.getForObject(omdbConfiguration.getBaseUrl() + "?t=" + title + "&apikey=" + omdbConfiguration.getApiKey(), OmdbResponseDto.class);
        if (omdbResponseDto.getImdbID() == null) {
            throw new MovieNotFoundException(String.format("Movie not found with title '%s'", title));
        }
        return omdbResponseDto;
    }

    @CircuitBreaker(name = "omdbApiCB", fallbackMethod = "findByImdbIDInCache")
    public OmdbResponseDto findByImdbID(String imdbID) {
        log.info("Searching movie with the imdbID '{}' in the omdbAPI", imdbID);
        OmdbResponseDto omdbResponseDto = restTemplate.getForObject(omdbConfiguration.getBaseUrl() + "?i=" + imdbID + "&apikey=" + omdbConfiguration.getApiKey(), OmdbResponseDto.class);
        if (omdbResponseDto.getImdbID() == null) {
            throw new MovieNotFoundException(String.format("Movie not found with imdbID '%s'", imdbID));
        }
        CACHE.put(imdbID, omdbResponseDto);
        return omdbResponseDto;
    }

    private OmdbResponseDto outOfService(String title, Throwable throwable) {
        throw new OmdbApiException(String.format("The OMDB is out of service, try again later: %s", throwable.getMessage()));
    }

    private OmdbResponseDto findByImdbIDInCache(String imdbID, Throwable throwable) {
        return CACHE.getOrDefault(imdbID, new OmdbResponseDto());
    }


}
