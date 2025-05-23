package com.openclassrooms.mddapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArticleRequest {
    @NotBlank(message = "Title cannot be empty")
    @NotNull(message = "Title cannot be null")
    private String title;

    @NotBlank(message = "Content cannot be empty")
    @NotNull(message = "Content cannot be null")
    private String content;

    @NotNull(message = "Theme ID is required")
    private Long themeId;
}
