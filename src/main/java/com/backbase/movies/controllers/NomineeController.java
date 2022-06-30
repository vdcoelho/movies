package com.backbase.movies.controllers;

import com.backbase.movies.dtos.NomineeInformationResponseDto;
import com.backbase.movies.services.CsvService;
import com.backbase.movies.services.NomineeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/nominees")
@AllArgsConstructor
public class NomineeController {
    private CsvService csvService;
    private NomineeService nomineeService;
    private static final String DEFAULT_CATEGORY = "Best Picture";

    @GetMapping("by-movie")
    public NomineeInformationResponseDto getNomineeInformation(
            @RequestParam String title,
            @RequestParam(defaultValue = DEFAULT_CATEGORY) String category) {
        return nomineeService.findByMovieTitleAndCategory(title, category);
    }

}
