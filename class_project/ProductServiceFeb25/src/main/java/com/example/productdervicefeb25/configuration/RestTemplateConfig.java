package com.example.productdervicefeb25.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {
    // Create a Bean of RestTemplate
    // Note: Name of the class and method doesn't make any difference.

    @Bean
    public RestTemplate createRestTemplate() {
        return new RestTemplate();
    }
}
