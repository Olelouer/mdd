package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.dto.AuthenticationRequest;
import com.openclassrooms.mddapi.dto.AuthenticationResponse;
import com.openclassrooms.mddapi.dto.RegisterRequest;
import com.openclassrooms.mddapi.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    /**
     * Handles user registration.
     *
     * @param registerRequest the request containing the user's registration details
     * @return a ResponseEntity containing an AuthenticationResponse object with the registration result
     */
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@Valid @RequestBody RegisterRequest registerRequest) {
        return ResponseEntity.ok(authService.register(registerRequest));
    }

    /**
     * Handles user authentication.
     *
     * @param authenticationRequest the request containing the user's authentication credentials
     * @return a ResponseEntity containing an AuthenticationResponse object with the authentication result
     */
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticate(@Valid @RequestBody AuthenticationRequest authenticationRequest) {
        return ResponseEntity.ok(authService.authenticate(authenticationRequest));
    }
}
