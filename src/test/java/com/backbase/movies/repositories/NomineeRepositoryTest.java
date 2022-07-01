package com.backbase.movies.repositories;

import com.backbase.movies.models.Nominee;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class NomineeRepositoryTest {

    @Autowired
    private NomineeRepository underTest;

    @Test
    void itShouldFindNominee() {
        //given
        String title = "Titanic";
        String category = "Best Picture";
        Nominee nomineed = Nominee.builder()
                .category("Best Picture")
                .nominated("Titanic")
                .won(true)
                .yearOfNominated("1997")
                .build();

        underTest.save(nomineed);

        //when
        Optional<Nominee> nominee = underTest.findFirstByCategoryIgnoreCaseAndNominatedIgnoreCase(category, title);

        //then
        assertThat(nominee.isPresent()).isTrue();
    }

    @Test
    void itShouldDoNotFind() {
        //given
        String title = "Titanic";
        String category = "Best Picture";

        //when
        Optional<Nominee> nominee = underTest.findFirstByCategoryIgnoreCaseAndNominatedIgnoreCase(category, title);

        //then
        assertThat(nominee.isEmpty()).isTrue();
    }
}