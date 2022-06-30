package com.backbase.movies.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NomineeInformationResponseDto {
    private String category;
    private Boolean wasWinner;
    private Boolean wasNominee;
    private String yearOfNominated;
}
