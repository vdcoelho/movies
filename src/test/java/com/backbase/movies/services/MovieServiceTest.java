package com.backbase.movies.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class MovieServiceTest {

    @Mock
    private OmdbService omdbService;
    private MovieService underTest;

    @BeforeEach
    void setup() {
        underTest = new MovieService(omdbService);
    }

    @Test
    void itShouldCallTheOmdbService() {
        //given
        String title = "Titanic";
        //when
        underTest.findByTitle(title);
        //then
        verify(omdbService).findByTitle(title);
    }
}