package com.openclassrooms.mddapi.mapper;

import com.openclassrooms.mddapi.dto.ArticleResponse;
import com.openclassrooms.mddapi.model.Article;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;
import java.util.List;

@Component
public class ArticleMapper {
    public ArticleResponse toResponse(Article article) {
        return ArticleResponse.builder()
                .id(article.getId())
                .title(article.getTitle())
                .content(article.getContent())
                .createdAt(article.getCreatedAt())
                .updatedAt(article.getUpdatedAt())
                .build();
    }

    public List<ArticleResponse> toResponseList (List<Article> articles) {
        return articles.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }
}
