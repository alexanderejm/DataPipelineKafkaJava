package com.example.authenticator.service.authenticatorservice.controller;

import com.example.authenticator.service.authenticatorservice.JwtAuthenticationResponse;
import com.example.authenticator.service.authenticatorservice.SignUpRequest;
import com.example.authenticator.service.authenticatorservice.SigninRequest;
import com.example.authenticator.service.authenticatorservice.service.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    @PostMapping("/signup")
    public ResponseEntity<JwtAuthenticationResponse> signup(@RequestBody SignUpRequest request) {
        return ResponseEntity.ok(authenticationService.signup(request));
    }

    @PostMapping("/signin")
    public ResponseEntity<JwtAuthenticationResponse> signin(@RequestBody SigninRequest request) {
        return ResponseEntity.ok(authenticationService.signin(request));
    }

    @PostMapping("/validateToken")
    public ResponseEntity<String> validateToken(@RequestBody String token) {
        if (authenticationService.isTokenValid(token)) {
            return ResponseEntity.ok("Token is valid");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
        }
    }
}
