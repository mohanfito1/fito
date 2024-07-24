package com.fitoverse.config;

import lombok.Builder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /* TODO
    *   As of now, we are not implementing the authtoken for all API's as
    *   postman requires the token to validate the request
    *   But later on, we should definitely do this part in order maintain security*/
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/api/v1/**","css/styles.css","/favicon.ico").permitAll() // Allow access to /api/** endpoints without authentication
                                .anyRequest().authenticated()
                )
                .csrf(csrf -> csrf.disable()); // Disable CSRF if necessary

        return http.build();
    }
}