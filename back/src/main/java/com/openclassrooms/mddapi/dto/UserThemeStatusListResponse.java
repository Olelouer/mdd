package com.openclassrooms.mddapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * DTO acting as a wrapper for a list of {@link UserThemeStatusResponse} objects.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserThemeStatusListResponse {
    private List<UserThemeStatusResponse> themes;
}