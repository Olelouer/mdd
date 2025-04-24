package com.openclassrooms.mddapi.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.openclassrooms.mddapi.model.User;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for User entity operations.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);
}
