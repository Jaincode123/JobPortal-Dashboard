package com.aayushi.jobportal;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .securityMatcher("/**") // Define the security matcher
//                .requiresChannel(channel -> channel
//                        .anyRequest().requiresSecure() // Force HTTPS
//                )
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/h2-console/**").permitAll()
                        .anyRequest().permitAll() // Allow all requests
                )
                .csrf(csrf -> csrf.ignoringRequestMatchers("/h2-console/**")) // Disable CSRF for H2 console
                .headers(headers -> headers.frameOptions(frameOptions -> frameOptions.disable()));
        return http.build();
    }
}
