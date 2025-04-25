package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.dto.ThemeListResponse;
import com.openclassrooms.mddapi.repository.ThemeRepository;
import com.openclassrooms.mddapi.mapper.ThemeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ThemeService {
    private final ThemeRepository themeRepository;
    private final ThemeMapper themeMapper;

    /**
     * Retrieves a list of all available themes.
     *
     * @return an ThemeListResponse object with the list of themes
     */
    public ThemeListResponse getAllThemes() {
        return new ThemeListResponse(themeMapper.toResponseList(themeRepository.findAll()));
    }
}
