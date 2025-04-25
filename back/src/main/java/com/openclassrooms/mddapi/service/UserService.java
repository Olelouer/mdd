package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.dto.GlobalMessageResponse;
import com.openclassrooms.mddapi.dto.UserRequest;
import com.openclassrooms.mddapi.dto.UserResponse;
import com.openclassrooms.mddapi.exception.EmailAlreadyExistsException;
import com.openclassrooms.mddapi.exception.UsernameAlreadyExistsException;
import com.openclassrooms.mddapi.mapper.UserMapper;
import com.openclassrooms.mddapi.model.User;
import com.openclassrooms.mddapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    /**
     * Retrieves the currently authenticated user.
     *
     * @return The authenticated User object.
     * @throws BadCredentialsException if the authenticated user is not found in the repository.
     */
    public UserResponse getCurrentUser() {
        Optional<User> userOptional = findByUsername();

        return userOptional
                .map(userMapper::toDto)
                .orElseThrow(() -> new BadCredentialsException("User not found"));
    }

    @Transactional
    public GlobalMessageResponse updateCurrentUser(UserRequest userRequest) {

        Optional<User> userOptional = findByUsername();
        User userToUpdate = userOptional.orElseThrow(() ->
                new BadCredentialsException("Authenticated user not found in repository, cannot update."));

        boolean needsUpdate = false;

        if (StringUtils.hasText(userRequest.getEmail()) && !userRequest.getEmail().equalsIgnoreCase(userToUpdate.getEmail())) {
            if (userRepository.existsByEmail(userRequest.getEmail())) {
                throw new EmailAlreadyExistsException("Email address '" + userRequest.getEmail() + "' is already in use.");
            }
            needsUpdate = true;
        }

        if (StringUtils.hasText(userRequest.getUsername()) && !userRequest.getUsername().equals(userToUpdate.getUsername())) {
            if (userRepository.existsByUsername(userRequest.getUsername())) {
                throw new UsernameAlreadyExistsException("Username '" + userRequest.getUsername() + "' is already taken.");
            }
            needsUpdate = true;
        }

        if (StringUtils.hasText(userRequest.getPassword())) {
            needsUpdate = true;
        }

        if (needsUpdate) {
            User updatedUser = userMapper.updateEntity(userToUpdate, userRequest);
            userRepository.save(updatedUser);
            return new GlobalMessageResponse("User updated successfully");
        } else {
            // Aucun champ valide à mettre à jour n'a été fourni
            return new GlobalMessageResponse("No changes detected or provided for update.");
        }
    }

    public Optional<User> findByUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new BadCredentialsException("User not authenticated");
        }
        String username = authentication.getName();

        return userRepository.findByUsername(username);
    }
}
