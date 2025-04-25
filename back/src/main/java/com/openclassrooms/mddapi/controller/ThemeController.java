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
     * Retrieves a list of all available themes.
     *
     * @return a ResponseEntity containing a ThemeListResponse object with the list of themes
     */
    @GetMapping
    public ResponseEntity<ThemeListResponse> getAllThemes() {
        return ResponseEntity.ok(themeService.getAllThemes());
    }
}
