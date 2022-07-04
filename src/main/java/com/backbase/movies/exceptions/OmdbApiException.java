package com.backbase.movies.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
public class OmdbApiException extends RuntimeException {

    public OmdbApiException(String message) {
        super(message);
    }
}
