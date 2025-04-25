package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.dto.ArticleListResponse;
import com.openclassrooms.mddapi.dto.ArticleRequest;
import com.openclassrooms.mddapi.dto.ArticleResponse;
import com.openclassrooms.mddapi.dto.GlobalMessageResponse;
import com.openclassrooms.mddapi.mapper.ArticleMapper;
import com.openclassrooms.mddapi.model.Article;
import com.openclassrooms.mddapi.model.Theme;
import com.openclassrooms.mddapi.model.User;
import com.openclassrooms.mddapi.repository.ArticleRepository;
import com.openclassrooms.mddapi.repository.ThemeRepository;
import com.openclassrooms.mddapi.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final ArticleMapper articleMapper;
    private final UserRepository userRepository;
    private final ThemeRepository themeRepository;

    /**
     * Creates a new article
     *
     * @param articleRequest the request containing the article's details
     *
     * @return a GlobalMessageResponse indicating the result of the operation
     */
    public GlobalMessageResponse createArticle(ArticleRequest articleRequest) {
        try {
            User user = userRepository.findById(articleRequest.getUserId())
                    .orElseThrow(() -> new EntityNotFoundException("User not found : " + articleRequest.getUserId()));

            Theme theme = themeRepository.findById(articleRequest.getThemeId())
                    .orElseThrow(() -> new EntityNotFoundException("Article not found with id: " + articleRequest.getThemeId()));

            Article article = articleMapper.toEntity(articleRequest);
            article.setAuthor(user);
            article.setTheme(theme);
            articleRepository.save(article);

            return new GlobalMessageResponse("Article created successfully");
        } catch (Exception ex) {
            log.error("Unexpected error while creating article: {}", ex.getMessage(), ex);
            return new GlobalMessageResponse(ex.getMessage());
        }
    }

    /**
     * Retrieves a list of all available articles.
     *
     * @return an ArticleListResponse object with the list of articles
     */
    public ArticleListResponse getAllArticles() {
        return new ArticleListResponse(articleMapper.toResponseList(articleRepository.findAll()));
    }

    /**
     * Retrieves a specific article by its ID.
     *
     * @param id the ID of the article to retrieve
     * @return an ArticleResponse object with the article's details
     * @throws IllegalArgumentException if the article is not found with the given ID
     */
    public ArticleResponse getArticle(Long id) {
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Article not found with id: " + id));
        return articleMapper.toResponse(article);
    }
}
