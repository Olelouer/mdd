package com.openclassrooms.mddapi.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentRequest {
    @NotNull(message = "Content is required")
    private String content;

    @NotNull(message = "User ID is required")
    private Long userId;

    @NotNull(message = "Article ID is required")
    private Long articleId;
}
