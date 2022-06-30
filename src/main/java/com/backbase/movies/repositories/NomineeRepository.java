package com.backbase.movies.repositories;

import com.backbase.movies.models.Nominee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NomineeRepository extends JpaRepository<Nominee, Integer> {

    Optional<Nominee> findFirstByCategoryIgnoreCaseAndNominatedIgnoreCase(String category, String nominated);
}
