package com.openclassrooms.mddapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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

    @NotNull(message = "Themes IDs is required")
    @NotEmpty(message = "At least one theme ID is required")
    private List<Long> themesIds;
}
