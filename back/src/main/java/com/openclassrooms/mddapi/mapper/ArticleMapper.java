package com.openclassrooms.mddapi.mapper;

import com.openclassrooms.mddapi.dto.ArticleRequest;
import com.openclassrooms.mddapi.dto.ArticleResponse;
import com.openclassrooms.mddapi.dto.ThemeResponse;
import com.openclassrooms.mddapi.model.Article;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.stream.Collectors;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ArticleMapper {
    private final ThemeMapper themeMapper;

    public ArticleResponse toResponse(Article article) {
        ThemeResponse themeResponse = null;
        if (article.getTheme() != null) {
            themeResponse = themeMapper.toResponse(article.getTheme());
        }

        return ArticleResponse.builder()
                .id(article.getId())
                .title(article.getTitle())
                .content(article.getContent())
                .createdAt(article.getCreatedAt())
                .updatedAt(article.getUpdatedAt())
                .theme(themeResponse)
                .build();
    }

    public List<ArticleResponse> toResponseList (List<Article> articles) {
        return articles.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public Article toEntity(ArticleRequest articleRequest) {
        return Article.builder()
                .title(articleRequest.getTitle())
                .content(articleRequest.getContent())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }
}
