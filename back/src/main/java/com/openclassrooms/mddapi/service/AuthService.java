package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.dto.AuthenticationRequest;
import com.openclassrooms.mddapi.model.User;
import com.openclassrooms.mddapi.dto.AuthenticationResponse;
import com.openclassrooms.mddapi.dto.RegisterRequest;
import com.openclassrooms.mddapi.mapper.UserMapper;
import com.openclassrooms.mddapi.repository.UserRepository;
import com.openclassrooms.mddapi.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserMapper userMapper;

    /**
     * Registers a new user, encodes their password, assigns a role, and generates a JWT token.
     *
     * @param request The registration request containing user details.
     * @return AuthenticationResponse containing the generated JWT token.
     */
    public AuthenticationResponse register(RegisterRequest request) {
        User user = userMapper.toEntity(request);

        userRepository.save(user);

        String jwtToken =  jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    /**
     * Authenticates a user by verifying credentials and generating a JWT token.
     *
     * @param request The authentication request containing email and password.
     * @return AuthenticationResponse containing the generated JWT token.
     */
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        String identifier = request.getEmail();
        if(!StringUtils.hasText(identifier)) {
            identifier = request.getUsername();
        }

        final String finalIdentifier = identifier;

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        identifier,
                        request.getPassword()
                )
        );

        var user = userRepository.findByUsername(finalIdentifier)
                .or(() -> userRepository.findByEmail(finalIdentifier))
                .orElseThrow();

        var jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

}
