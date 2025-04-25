package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.dto.ArticleListResponse;
import com.openclassrooms.mddapi.dto.ArticleResponse;
import com.openclassrooms.mddapi.mapper.ArticleMapper;
import com.openclassrooms.mddapi.model.Article;
import com.openclassrooms.mddapi.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final ArticleMapper articleMapper;

    /**
     * Retrieves all articles
     *
     * @return List of articles
     */
    public ArticleListResponse getAllArticles() {
        return new ArticleListResponse(articleMapper.toResponseList(articleRepository.findAll()));
    }

    /**
     * Retrieves an article by id
     *
     * @param id
     *
     * @return Article
     */
    public ArticleResponse getArticle(Long id) {
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Article not found with id: " + id));
        return articleMapper.toResponse(article);
    }
}
