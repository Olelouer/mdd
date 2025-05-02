package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.dto.GlobalMessageResponse;
import com.openclassrooms.mddapi.dto.ThemeListResponse;
import com.openclassrooms.mddapi.dto.UserThemeStatusListResponse;
import com.openclassrooms.mddapi.dto.UserThemeStatusResponse;
import com.openclassrooms.mddapi.model.Theme;
import com.openclassrooms.mddapi.model.User;
import com.openclassrooms.mddapi.repository.ThemeRepository;
import com.openclassrooms.mddapi.mapper.ThemeMapper;
import com.openclassrooms.mddapi.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ThemeService {
    private final ThemeRepository themeRepository;
    private final ThemeMapper themeMapper;
    private final UserRepository userRepository;

    /**
     * Retrieves a list of all available themes.
     *
     * @return an ThemeListResponse object with the list of themes
     */
    public ThemeListResponse getAllThemes() {
        return new ThemeListResponse(themeMapper.toResponseList(themeRepository.findAll()));
    }

    /**
     * Retrieves a list of all available themes with current user's subscription status.
     *
     * @param currentUser Currently authenticated user
     * @return UserThemeStatusListResponse containing the list of themes with their subscription status
     */
    @Transactional(readOnly = true)
    public UserThemeStatusListResponse getAllThemesWithSubscriptionStatus(User currentUser) {
        List<Theme> allThemes = themeRepository.findAll();
        Set<Long> subscribedThemeIds = Collections.emptySet();

        if (currentUser != null) {
            Long currentUserId = currentUser.getId();

            Optional<User> managedUserOpt = userRepository.findById(currentUserId);

            if(managedUserOpt.isPresent()) {
                User managedUser = managedUserOpt.get();
                if (managedUser.getSubscribedThemes() != null) {
                    subscribedThemeIds = managedUser.getSubscribedThemes().stream()
                            .map(Theme::getId)
                            .collect(Collectors.toSet());
                }
            }
        }

        List<UserThemeStatusResponse> userThemeStatusResponses =
                themeMapper.toUserThemeStatusResponseList(allThemes, subscribedThemeIds);

        return new UserThemeStatusListResponse(userThemeStatusResponses);
    }

    /**
     * Subscribes a user identified by email to a specific theme.
     *
     * @param userEmail User's email address.
     * @param themeId Theme ID
     * @return Message if subscription successful.
     * @throws EntityNotFoundException if the user or theme is not found.
     * @throws IllegalArgumentException if the user is already a subscriber.
     */
    @Transactional
    public GlobalMessageResponse subscribeUserToTheme(String userEmail, Long themeId) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new EntityNotFoundException("User not found with email: " + userEmail));

        Theme theme = themeRepository.findById(themeId)
                .orElseThrow(() -> new EntityNotFoundException("Theme not found with ID: " + themeId));

        if (user.getSubscribedThemes().contains(theme)) {
            throw new IllegalArgumentException("The user is already subscribed to this theme.");
        }

        user.getSubscribedThemes().add(theme);

        userRepository.save(user);

        return new GlobalMessageResponse("Successful theme subscription " + themeId);
    }

    /**
     * Unsubscribes authenticated user from a theme
     *
     * @param userEmail User's email address.
     * @param themeId Theme ID
     * @return Message if unsubscription successful.
     * @throws EntityNotFoundException if the user or theme is not found.
     * @throws IllegalArgumentException if the user was not subscribed to this theme.
     */
    @Transactional
    public GlobalMessageResponse unsubscribeUserFromTheme(String userEmail, Long themeId) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new EntityNotFoundException("User not found with email : " + userEmail));

        Theme theme = themeRepository.findById(themeId)
                .orElseThrow(() -> new EntityNotFoundException("Theme not found with ID : " + themeId));

        if (!user.getSubscribedThemes().contains(theme)) {
            throw new IllegalArgumentException("The user is not subscribed to this theme");
        }

        user.getSubscribedThemes().remove(theme);

        userRepository.save(user);

        return new GlobalMessageResponse("Successful unsubscription to theme " + themeId);
    }
}
