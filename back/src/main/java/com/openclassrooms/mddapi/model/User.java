package com.openclassrooms.mddapi.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity representing a User in the system, implementing Spring Security's UserDetails interface.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = "email"),
        @UniqueConstraint(columnNames = "name")
})
@Schema(description = "Entity representing a registered user in the system")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @NotNull(message = "Username is required")
    @NotBlank(message = "Username cannot be empty")
    private String username;

    @Column(unique = true)
    @NotNull(message = "Email is required")
    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Email format is invalid")
    private String email;

    @NotNull(message = "Password is required")
    @NotBlank(message = "Password cannot be empty")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?~]).{8,}$",
            message = "Password must be at least 8 characters long and contain at least one digit, one lowercase letter, one uppercase letter, and one special character.")
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Article> articles = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "user_theme_subscriptions",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "theme_id")
    )
    private List<Theme> subscribedThemes = new ArrayList<>();

    @Column(name= "created_at", nullable = false)
    @JsonProperty("created_at")
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    private LocalDateTime createdAt;

    @Column(name= "updated_at", nullable = false)
    @JsonProperty("updated_at")
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    private LocalDateTime updatedAt;

    /**
     * Returns the authorities granted to the user based on their role.
     *
     * @return A collection of granted authorities.
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + role.name()));
    }

    /**
     * Returns the username.
     *
     * @return The user's username.
     */
    @Override
    public String getUsername() {
        return username;
    }

    /**
     * Returns the encrypted password of the user.
     *
     * @return The encrypted password.
     */
    @Override
    public String getPassword() {
        return password;
    }

    /**
     * Indicates whether the user's account has expired.
     *
     * @return true, as accounts never expire in this implementation.
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Indicates whether the user is locked or unlocked.
     *
     * @return true, as accounts are never locked in this implementation.
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * Indicates whether the user's credentials (password) have expired.
     *
     * @return true, as credentials never expire in this implementation.
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Indicates whether the user is enabled or disabled.
     *
     * @return true, as all users are enabled in this implementation.
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}
