package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.dto.GlobalMessageResponse;
import com.openclassrooms.mddapi.dto.ThemeListResponse;
import com.openclassrooms.mddapi.model.User;
import com.openclassrooms.mddapi.service.ThemeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

/**
 * REST Controller handling themes operations (CRUD).
 */
@RestController
@RequestMapping("/api/themes")
@RequiredArgsConstructor
public class ThemeController {
    private final ThemeService themeService;

    /**
     * Retrieves a list of all available themes.
     *
     * @return a ResponseEntity containing a ThemeListResponse object with the list of themes
     */
    @GetMapping
    public ResponseEntity<ThemeListResponse> getAllThemes() {
        return ResponseEntity.ok(themeService.getAllThemes());
    }

    /**
     * ASubscribes the authenticated user to a specific theme.
     *
     * @param themeId Theme ID
     * @param currentUser Authenticated user object
     * @return Success message
     */
    @PostMapping("/{themeId}/subscribe")
    public ResponseEntity<GlobalMessageResponse> subscribeToTheme(
            @PathVariable Long themeId,
            @AuthenticationPrincipal User currentUser
    ) {
        String userEmail = currentUser.getEmail();
        return ResponseEntity.ok(themeService.subscribeUserToTheme(userEmail, themeId));
    }

    /**
     * Handles HTTP request to unsubscribe authenticated user to theme
     *
     * @param themeId Theme ID
     * @param currentUser Authenticated user object
     * @return Success message
     */
    @DeleteMapping("/{themeId}/unsubscribe")
    public ResponseEntity<GlobalMessageResponse> unsubscribeFromTheme(
            @PathVariable Long themeId,
            @AuthenticationPrincipal User currentUser
    ) {
        String userEmail = currentUser.getEmail();
        return ResponseEntity.ok(themeService.unsubscribeUserFromTheme(userEmail, themeId));
    }
}
