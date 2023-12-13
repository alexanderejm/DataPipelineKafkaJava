package com.example.order.service.orderservice.service;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AuthService {

    @Autowired
    private RestTemplate restTemplate;

    public void processOrder(HttpServletRequest request) {
        String token = extractToken(request);

        // Send a request to authentication-service to validate the token
        String authValidationUrl = "http://authentication-service/api/auth/validateToken";
        ResponseEntity<String> response = restTemplate.postForEntity(authValidationUrl, token, String.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            // Token is valid, proceed with processing the order
        } else {
            // Invalid token, handle accordingly
        }
    }

    private String extractToken(HttpServletRequest request) {
        // Logic to extract the token from the request headers or cookies
        return "";
    }
}
