package com.backbase.movies.services;

import com.backbase.movies.dtos.NomineeInformationResponseDto;
import com.backbase.movies.models.Nominee;
import com.backbase.movies.repositories.NomineeRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class NomineeService {
    private final NomineeRepository nomineeRepository;
    private final MovieService movieService;

    public NomineeInformationResponseDto findByMovieTitleAndCategory(String title, String category) {
        log.info("Searching for '{}' nominees for '{}'", category, title);
        Optional<Nominee> nominee = nomineeRepository.findFirstByCategoryIgnoreCaseAndNominatedIgnoreCase(category, title);

        if (nominee.isPresent()) {
            return NomineeInformationResponseDto.builder()
                    .category(nominee.get().getCategory())
                    .wasNominee(true)
                    .yearOfNominated(nominee.get().getYearOfNominated())
                    .wasWinner(nominee.get().getWon())
                    .build();
        }

        log.info("Nominees not found, then checking if the movie exists");
        movieService.findByTitle(title);

        return NomineeInformationResponseDto.builder()
                .category(category)
                .wasNominee(false)
                .yearOfNominated(null)
                .wasWinner(false)
                .build();

    }

}
