package com.openclassrooms.mddapi.mapper;

import com.openclassrooms.mddapi.dto.*;
import com.openclassrooms.mddapi.model.Article;
import com.openclassrooms.mddapi.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.stream.Collectors;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ArticleMapper {
    private final ThemeMapper themeMapper;
    private final UserMapper userMapper;
    private final CommentMapper commentMapper;

    public ArticleResponse toResponse(Article article, User currentUser) {
        UserResponse userResponse = null;

        if (article.getAuthor() != null) {
            userResponse = userMapper.toResponse(article.getAuthor());
        }

        List<ThemeResponse> themeResponses = themeMapper.toResponseList(article.getAssociatedThemes());

        List<CommentResponse> commentResponses = commentMapper.toResponseList(article.getComments());

        boolean currentUserLiked = false;
        if (currentUser != null && article.getLikes() != null) {
            currentUserLiked = article.getLikes().stream()
                    .anyMatch(likingUser -> likingUser.getUser().getId().equals(currentUser.getId()));
        }

        return ArticleResponse.builder()
                .id(article.getId())
                .title(article.getTitle())
                .content(article.getContent())
                .createdAt(article.getCreatedAt())
                .updatedAt(article.getUpdatedAt())
                .themes(themeResponses)
                .author(userResponse)
                .comments(commentResponses)
                .likeCount(article.getLikes().size())
                .liked(currentUserLiked)
                .build();
    }

    public List<ArticleResponse> toResponseList (List<Article> articles, User currentUser) {
        return articles.stream()
                .map(article -> this.toResponse(article, currentUser))
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
