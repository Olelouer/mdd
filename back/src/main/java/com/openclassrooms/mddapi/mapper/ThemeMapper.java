package com.openclassrooms.mddapi.mapper;

import com.openclassrooms.mddapi.dto.ThemeResponse;
import com.openclassrooms.mddapi.model.Theme;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;
import java.util.List;

@Component
public class ThemeMapper {
    public ThemeResponse toResponse(Theme theme) {
        return ThemeResponse.builder()
                .id(theme.getId())
                .title(theme.getTitle())
                .description(theme.getDescription())
                .createdAt(theme.getCreatedAt())
                .updatedAt(theme.getUpdatedAt())
                .build();
    }

    public List<ThemeResponse> toResponseList(List<Theme> themes) {
        return themes.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }
}
