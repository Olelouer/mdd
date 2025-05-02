package com.openclassrooms.mddapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * DTO representing a Theme's details along with the current user's subscription status.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserThemeStatusResponse {
    private Long id;
    private String title;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private boolean subscribed;
}