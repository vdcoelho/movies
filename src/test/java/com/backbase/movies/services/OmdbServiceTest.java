package com.backbase.movies.services;

import com.backbase.movies.configuration.OmdbConfiguration;
import com.backbase.movies.dtos.OmdbResponseDto;
import com.backbase.movies.exceptions.MovieNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class OmdbServiceTest {

    @Mock
    private RestTemplate restTemplate;
    @Mock
    private OmdbConfiguration omdbConfiguration;
    private OmdbService underTest;

    @BeforeEach
    void setUp() {
        underTest = new OmdbService(restTemplate, omdbConfiguration);
    }

    @Test
    void itShouldThrowMovieNotFoundException() {
        //given
        String title = "tsgdds";
        given(restTemplate.getForObject(omdbConfiguration.getBaseUrl() + "?t=" + title + "&apikey=" + omdbConfiguration.getApiKey(), OmdbResponseDto.class))
                .willReturn(OmdbResponseDto.builder().build());

        //when
        //then
        assertThatThrownBy(() -> underTest.findByTitle(title))
                .isInstanceOf(MovieNotFoundException.class)
                .hasMessageContaining(String.format("Movie not found with title '%s'", title));
    }

    @Test
    void itShouldReturnAMovieById() {
        //given
        String imdbID = "ID1";
        given(restTemplate.getForObject(omdbConfiguration.getBaseUrl() + "?i=" + imdbID + "&apikey=" + omdbConfiguration.getApiKey(), OmdbResponseDto.class))
                .willReturn(OmdbResponseDto.builder().imdbID("ID1").build());

        //when
        OmdbResponseDto result = underTest.findByImdbID(imdbID);
        //then
        assertThat(result).isEqualTo(OmdbResponseDto.builder().imdbID("ID1").build());
    }

    @Test
    void itShouldThrowMovieNotFoundExceptionByImdbID() {
        //given
        String imdbID = "X01";
        given(restTemplate.getForObject(omdbConfiguration.getBaseUrl() + "?i=" + imdbID + "&apikey=" + omdbConfiguration.getApiKey(), OmdbResponseDto.class))
                .willReturn(OmdbResponseDto.builder().build());

        //when
        //then
        assertThatThrownBy(() -> underTest.findByImdbID(imdbID))
                .isInstanceOf(MovieNotFoundException.class)
                .hasMessageContaining(String.format("Movie not found with imdbID '%s'", imdbID));
    }

    @Test
    void itShouldReturnAMovie() {
        //given
        String title = "Titanic";
        given(restTemplate.getForObject(omdbConfiguration.getBaseUrl() + "?t=" + title + "&apikey=" + omdbConfiguration.getApiKey(), OmdbResponseDto.class))
                .willReturn(OmdbResponseDto.builder().imdbID("ID1").build());

        //when
        OmdbResponseDto result = underTest.findByTitle(title);
        //then
        assertThat(result).isEqualTo(OmdbResponseDto.builder().imdbID("ID1").build());
    }


}