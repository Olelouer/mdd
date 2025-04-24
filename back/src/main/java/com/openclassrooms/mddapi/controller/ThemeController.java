package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.dto.ThemeListResponse;
import com.openclassrooms.mddapi.service.ThemeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST Controller handling themes operations (CRUD).
 */
@RestController
@RequestMapping("/api/themes")
@RequiredArgsConstructor
public class ThemeController {
    private final ThemeService themeService;

    /**
     * Retrieves all themes
     *
     * @return List of themes
     */
    @GetMapping
    public ResponseEntity<ThemeListResponse> getAllThemes() {
        return ResponseEntity.ok(themeService.getAllThemes());
    }
}
