package com.backbase.movies.repositories;

import com.backbase.movies.dtos.RatingResponseDto;
import com.backbase.movies.models.Rating;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class RatingRepositoryTest {

    @Autowired
    private RatingRepository underTest;

    @BeforeAll
    void setup() {
        Rating rating1 = Rating.builder()
                .imdbID("ID1")
                .rating(new BigDecimal(5))
                .build();
        Rating rating2 = Rating.builder()
                .imdbID("ID1")
                .rating(new BigDecimal(3))
                .build();

        Rating rating3 = Rating.builder()
                .imdbID("ID2")
                .rating(new BigDecimal(5))
                .build();

        Rating rating4 = Rating.builder()
                .imdbID("ID3")
                .rating(new BigDecimal(3))
                .build();

        Rating[] ratings = {rating1, rating2, rating3, rating4};
        underTest.saveAll(Arrays.asList(ratings));
    }

    @Test
    void itShouldContainsThreeMovies() {
        List<RatingResponseDto> top10Rated = underTest.listTopRated(Pageable.ofSize(10));

        assertThat(top10Rated.size()).isEqualTo(3);
    }

    @Test
    void itShouldBeATopRated() {
        List<RatingResponseDto> top10Rated = underTest.listTopRated(Pageable.ofSize(10));

        assertThat(top10Rated.get(0).getImdbID()).isEqualTo("ID2");
    }

    @Test
    void itShouldBeASecondRated() {
        List<RatingResponseDto> top10Rated = underTest.listTopRated(Pageable.ofSize(10));

        assertThat(top10Rated.get(1).getImdbID()).isEqualTo("ID1");
    }

    @Test
    void itShouldContainsTwoVotes() {
        List<RatingResponseDto> top10Rated = underTest.listTopRated(Pageable.ofSize(10));

        assertThat(top10Rated.get(1).getVotes()).isEqualTo(2);
    }
}