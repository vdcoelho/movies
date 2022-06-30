package com.backbase.movies.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("omdb")
@Data
public class OmdbConfiguration {

    private String baseUrl;
    private String apiKey;
}
