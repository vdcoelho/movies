package com.backbase.movies.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class NomineeInformationResponseDto {
    private String category;
    private Boolean wasWinner;
    private Boolean wasNominee;
    private String yearOfNominated;
}
