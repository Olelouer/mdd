package com.openclassrooms.mddapi.mapper;

import com.openclassrooms.mddapi.dto.RegisterRequest;
import com.openclassrooms.mddapi.dto.UserRequest;
import com.openclassrooms.mddapi.dto.UserResponse;
import com.openclassrooms.mddapi.model.Role;
import com.openclassrooms.mddapi.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class UserMapper {
    private final PasswordEncoder passwordEncoder;

    public User toEntity(RegisterRequest request) {
        return User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .username(request.getUsername())
                .role(Role.USER)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }

    public UserResponse toResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .role(user.getRole().name())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }

    public User updateEntity(User user, UserRequest userRequest) {
        boolean updated = false;

        if (StringUtils.hasText(userRequest.getEmail()) && !userRequest.getEmail().equals(user.getEmail())) {
            user.setEmail(userRequest.getEmail());
            updated = true;
        }

        if (StringUtils.hasText(userRequest.getUsername()) && !userRequest.getUsername().equals(user.getUsername())) {
            user.setUsername(userRequest.getUsername());
            updated = true;
        }

        if (StringUtils.hasText(userRequest.getPassword()) && !userRequest.getPassword().equals(user.getPassword())) {
            user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
            updated = true;
        }

        if (updated) {
            user.setUpdatedAt(LocalDateTime.now());
        }
        return user;
    }
}
