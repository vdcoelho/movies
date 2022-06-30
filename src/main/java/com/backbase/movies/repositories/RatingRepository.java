package com.backbase.movies.repositories;

import com.backbase.movies.dtos.RatingResponseDto;
import com.backbase.movies.models.Rating;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {

    @Query("SELECT new com.backbase.movies.dtos.RatingResponseDto(r.imdbID, round(avg(r.rating),2), count(1)) FROM Rating r group by r.imdbID order by avg(r.rating) desc ")
    List<RatingResponseDto> listTopRated(Pageable pageable);
}