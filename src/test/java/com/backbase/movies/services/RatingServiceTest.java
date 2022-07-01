package com.backbase.movies.services;

import com.backbase.movies.dtos.OmdbResponseDto;
import com.backbase.movies.dtos.RatingRequestDto;
import com.backbase.movies.dtos.RatingResponseDto;
import com.backbase.movies.models.Rating;
import com.backbase.movies.repositories.RatingRepository;
import com.backbase.movies.utils.OmdbApiUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class RatingServiceTest {

    @Mock
    private RatingRepository ratingRepository;
    @Mock
    private OmdbService omdbService;

    private OmdbApiUtils omdbApiUtils = new OmdbApiUtils();
    private RatingService underTest;

    @BeforeEach
    void setUp() {
        underTest = new RatingService(ratingRepository, omdbService, omdbApiUtils);
    }

    @Test
    void itWillBeRate() {
        //given
        RatingRequestDto ratingRequestDto = RatingRequestDto.builder()
                .title("Titanic")
                .rate(4)
                .build();

        given(omdbService.findByTitle(ratingRequestDto.getTitle()))
                .willReturn(OmdbResponseDto.builder().imdbID("ID1").build());

        //when
        underTest.rate(ratingRequestDto);

        //then
        ArgumentCaptor<Rating> ratingArgumentCaptor = ArgumentCaptor.forClass(Rating.class);
        verify(ratingRepository).save(ratingArgumentCaptor.capture());
        Rating capturedRating = ratingArgumentCaptor.getValue();

        assertThat(capturedRating.getImdbID()).isEqualTo("ID1");
        assertThat(capturedRating.getRating()).isEqualTo(new BigDecimal(4));
    }

    @Test
    void getTop10RatedByBoxOffice() {
        //given
        RatingResponseDto ratingOne   = RatingResponseDto.builder().imdbID("id1").rating(4d).build();
        RatingResponseDto ratingTwo   = RatingResponseDto.builder().imdbID("id2").rating(3d).build();
        RatingResponseDto ratingThree = RatingResponseDto.builder().imdbID("id3").rating(4d).build();
        RatingResponseDto ratingFour  = RatingResponseDto.builder().imdbID("id4").rating(5d).build();
        List<RatingResponseDto> ratings = List.of(ratingOne, ratingTwo, ratingThree, ratingFour);

        given(ratingRepository.listTopRated(Pageable.ofSize(10)))
                .willReturn(ratings);

        given(omdbService.findByImdbID("id1"))
                .willReturn(OmdbResponseDto.builder().title("title1").boxOffice("1000").build());
        given(omdbService.findByImdbID("id2"))
                .willReturn(OmdbResponseDto.builder().title("title2").boxOffice("1500").build());
        given(omdbService.findByImdbID("id3"))
                .willReturn(OmdbResponseDto.builder().title("title3").boxOffice("N/A").build());
        given(omdbService.findByImdbID("id4"))
                .willReturn(OmdbResponseDto.builder().title("title4").boxOffice("N/A").build());

        //when
        List<RatingResponseDto> result = underTest.getTop10RatedByBoxOffice();

        //then
        assertThat(result.size()).isEqualTo(4);
        assertThat(result.get(0).getImdbID()).isEqualTo("id2");
        assertThat(result.get(1).getImdbID()).isEqualTo("id1");
        assertThat(result.get(2).getImdbID()).isEqualTo("id4");
        assertThat(result.get(3).getImdbID()).isEqualTo("id3");

    }
}