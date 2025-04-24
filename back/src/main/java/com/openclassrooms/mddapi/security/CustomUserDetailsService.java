package com.openclassrooms.mddapi.security;

import com.openclassrooms.mddapi.model.User;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.openclassrooms.mddapi.repository.UserRepository;

import lombok.RequiredArgsConstructor;

import java.util.Optional;

/**
 * Custom implementation of UserDetailsService for email-based authentication.
 */
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    /**
     * Loads a user by username or email address for authentication.
     *
     * @param usernameOrEmail User's username or email
     *
     * @return UserDetails if found
     * @throws UsernameNotFoundException if user is not found
     */
    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        Optional<User> userOpt = userRepository.findByUsername(usernameOrEmail);

        if(userOpt.isPresent()) {
            return userOpt.get();
        }

        userOpt = userRepository.findByEmail(usernameOrEmail);

        if(userOpt.isPresent()) {
            return userOpt.get();
        }

        throw new UsernameNotFoundException("User not found with identifier: " + usernameOrEmail);
    }
}