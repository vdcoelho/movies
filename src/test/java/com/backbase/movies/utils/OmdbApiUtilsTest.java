package com.backbase.movies.utils;

import com.backbase.movies.dtos.RatingResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class OmdbApiUtilsTest {

    private OmdbApiUtils underTest;

    @BeforeEach
    void setUp() {
        underTest = new OmdbApiUtils();
    }


    @Test
    void itShouldBeNull() {
        //given
        String value = null;
        //when
        BigDecimal result = underTest.stringToBigDecimal(value);
        //then
        assertThat(result).isNull();
    }

    @Test
    void itShouldBeNull2() {
        //given
        String value = "N/A";
        //when
        BigDecimal result = underTest.stringToBigDecimal(value);
        //then
        assertThat(result).isNull();
    }

    @Test
    void itShouldBeNull3() {
        //given
        String value = "";
        //when
        BigDecimal result = underTest.stringToBigDecimal(value);
        //then
        assertThat(result).isNull();
    }

    @Test
    void itShouldBeTenThousands() {
        //given
        String value = "10,000";
        //when
        BigDecimal result = underTest.stringToBigDecimal(value);
        //then
        assertThat(result).isEqualByComparingTo(new BigDecimal(10000));
    }

    @Test
    void itShouldOrderByBoxOfficeInverted() {
        //given
        List<RatingResponseDto> ratings = new ArrayList<>();
        ratings.add(RatingResponseDto.builder().boxOffice(null).build());
        ratings.add(RatingResponseDto.builder().boxOffice(new BigDecimal(100)).build());
        ratings.add(RatingResponseDto.builder().boxOffice(new BigDecimal(200)).build());

        //when
        List<RatingResponseDto> result = ratings.stream().sorted(OmdbApiUtils.boxOfficeComparatorInverted).collect(Collectors.toList());

        //then
        assertThat(result.size()).isEqualTo(3);
        assertThat(result.get(0)).isEqualTo(ratings.get(2));
        assertThat(result.get(1)).isEqualTo(ratings.get(1));
        assertThat(result.get(2)).isEqualTo(ratings.get(0));
    }

    @Test
    void itShouldOrderByRatingInverted() {
        //given
        List<RatingResponseDto> ratings = new ArrayList<>();
        ratings.add(RatingResponseDto.builder().rating(4.1d).build());
        ratings.add(RatingResponseDto.builder().rating(4.2d).build());
        ratings.add(RatingResponseDto.builder().rating(3.5d).build());
        //when
        List<RatingResponseDto> result = ratings.stream().sorted(OmdbApiUtils.ratingComparatorInverted).collect(Collectors.toList());

        //then
        assertThat(result.size()).isEqualTo(3);
        assertThat(result.get(0)).isEqualTo(ratings.get(1));
        assertThat(result.get(1)).isEqualTo(ratings.get(0));
        assertThat(result.get(2)).isEqualTo(ratings.get(2));
    }

    }
