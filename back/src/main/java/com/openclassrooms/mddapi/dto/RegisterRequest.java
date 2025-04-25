package com.openclassrooms.mddapi.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    @NotBlank(message = "Email cannot be empty")
    @NotNull(message = "Email is required")
    @Email(message = "Email format is invalid")
    private String email;

    @NotNull(message = "Username is required")
    @NotBlank(message = "Username cannot be empty")
    private String username;

    @NotNull(message = "Password is required")
    @NotBlank(message = "Password cannot be empty")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?~]).{8,}$",
            message = "Password must be at least 8 characters long and contain at least one digit, one lowercase letter, one uppercase letter, and one special character.")
    private String password;
}
