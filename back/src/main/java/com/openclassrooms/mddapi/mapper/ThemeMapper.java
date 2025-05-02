package com.openclassrooms.mddapi.mapper;

import com.openclassrooms.mddapi.dto.ThemeResponse;
import com.openclassrooms.mddapi.dto.UserThemeStatusResponse;
import com.openclassrooms.mddapi.model.Theme;
import org.springframework.stereotype.Component;

import java.util.Set;
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

    /**
     * Maps a Theme entity to a UserThemeStatusResponse DTO,
     *
     * @param theme The Theme entity to map.
     * @param subscribedThemeIds A set containing the IDs of themes the current user is subscribed to.
     * @return The corresponding UserThemeStatusResponse DTO.
     */
    public UserThemeStatusResponse toUserThemeStatusResponse(Theme theme, Set<Long> subscribedThemeIds) {
        if (theme == null) {
            return null;
        }
        final boolean isSubscribed = (subscribedThemeIds != null && subscribedThemeIds.contains(theme.getId()));

        return UserThemeStatusResponse.builder()
                .id(theme.getId())
                .title(theme.getTitle())
                .description(theme.getDescription())
                .createdAt(theme.getCreatedAt())
                .updatedAt(theme.getUpdatedAt())
                .subscribed(isSubscribed)
                .build();
    }

    /**
     * Maps a list of Theme entities to a list of UserThemeStatusResponse DTOs.
     *
     * @param themes The list of Theme entities.
     * @param subscribedThemeIds A set containing the IDs of themes the current user is subscribed to.
     * @return The list of UserThemeStatusResponse DTOs.
     */
    public List<UserThemeStatusResponse> toUserThemeStatusResponseList(List<Theme> themes, Set<Long> subscribedThemeIds) {
        if (themes == null) {
            return List.of();
        }

        final Set<Long> finalSubscribedIds = (subscribedThemeIds == null) ? Set.of() : subscribedThemeIds;

        return themes.stream()
                .map(theme -> toUserThemeStatusResponse(theme, finalSubscribedIds))
                .collect(Collectors.toList());
    }
}
