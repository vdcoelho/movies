package com.backbase.movies.services;

import com.backbase.movies.dtos.NomineeInformationResponseDto;
import com.backbase.movies.models.Nominee;
import com.backbase.movies.repositories.NomineeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class NomineeServiceTest {
    @Mock
    private NomineeRepository nomineeRepository;
    @Mock
    private MovieService movieService;

    private NomineeService underTest;

    @BeforeEach
    void setUp() {
        underTest = new NomineeService(nomineeRepository, movieService);
    }

    @Test
    void itShouldFindNominee() {
        //given
        String title = "Titanic";
        String category = "Best Picture";
        Optional<Nominee> nominee = Optional.of(
                Nominee.builder()
                        .category(category)
                        .won(true)
                        .yearOfNominated("1997")
                        .nominated(title)
                .build());

        given(nomineeRepository.findFirstByCategoryIgnoreCaseAndNominatedIgnoreCase(category, title))
                .willReturn(nominee);

        //when
        NomineeInformationResponseDto result = underTest.findByMovieTitleAndCategory(title, category);

        //then
        assertThat(result).isEqualTo(
                NomineeInformationResponseDto.builder()
                        .category(category)
                        .wasNominee(true)
                        .yearOfNominated("1997")
                        .wasWinner(true)
                        .build()
        );
    }

    @Test
    void itShouldNotFindNominee() {
        //given
        String title = "Titanic";
        String category = "Best Picture";
        Optional<Nominee> nominee = Optional.empty();

        given(nomineeRepository.findFirstByCategoryIgnoreCaseAndNominatedIgnoreCase(category, title))
                .willReturn(nominee);

        //when
        NomineeInformationResponseDto result = underTest.findByMovieTitleAndCategory(title, category);

        //then
        assertThat(result).isEqualTo(
                NomineeInformationResponseDto.builder()
                        .category(category)
                        .wasNominee(false)
                        .yearOfNominated(null)
                        .wasWinner(false)
                        .build()
        );
    }
}