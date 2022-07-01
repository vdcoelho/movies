package com.backbase.movies.services;

import com.backbase.movies.repositories.NomineeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CsvServiceTest {
    @Mock
    private NomineeRepository nomineeRepository;
    private CsvService underTest;

    @BeforeEach
    void setUp() {
        underTest = new CsvService(nomineeRepository);
    }

    @Test
    void itShouldLoadTheEntireCSVFile() {
        //when
        Integer quantity = underTest.loadDB();
        //then
        assertThat(quantity).isEqualTo(10137);
    }

    @Test
    void itShouldCallDeleteAll() {
        //when
        underTest.clean();
        //then
        verify(nomineeRepository).deleteAll();
    }
}