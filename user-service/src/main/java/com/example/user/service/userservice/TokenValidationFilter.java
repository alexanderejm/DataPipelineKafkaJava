package com.example.user.service.userservice;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class TokenValidationFilter extends OncePerRequestFilter {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // Logic to extract and validate the token
        String token = extractToken(request);

        // Validate the token (e.g., by sending a request to authentication-service)
        boolean isValid = validateToken(token);

        if (isValid) {
            // If the token is valid, proceed with the filter chain
            filterChain.doFilter(request, response);
        } else {
            // If the token is invalid, send an unauthorized response
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }

    private String extractToken(HttpServletRequest request) {
        // Get the Authorization header
        String authorizationHeader = request.getHeader("Authorization");

        // Extract the token from the header
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.substring(7); // Skip "Bearer "
        }

        return null;
    }

    private boolean validateToken(String token) {
        // Logic to validate the token (e.g., by sending a request to authentication-service)
        // Return true if the token is valid, false otherwise

        // Send a request to authentication-service to validate the token
        String authValidationUrl = "http://localhost:8086/api/v1/auth/validateToken";

        ResponseEntity<String> response = restTemplate.postForEntity(authValidationUrl, token, String.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            // Token is valid, proceed with processing the order
            return true;
        } else {
            // Invalid token, handle accordingly
        }
        return false;
    }
}