package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.dto.ArticleListResponse;
import com.openclassrooms.mddapi.dto.ArticleRequest;
import com.openclassrooms.mddapi.dto.ArticleResponse;
import com.openclassrooms.mddapi.dto.GlobalMessageResponse;
import com.openclassrooms.mddapi.mapper.ArticleMapper;
import com.openclassrooms.mddapi.model.Article;
import com.openclassrooms.mddapi.model.ArticleLike;
import com.openclassrooms.mddapi.model.Theme;
import com.openclassrooms.mddapi.model.User;
import com.openclassrooms.mddapi.repository.ArticleLikeRepository;
import com.openclassrooms.mddapi.repository.ArticleRepository;
import com.openclassrooms.mddapi.repository.ThemeRepository;
import com.openclassrooms.mddapi.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final ArticleMapper articleMapper;
    private final UserRepository userRepository;
    private final ThemeRepository themeRepository;
    private final ArticleLikeRepository articleLikeRepository;

    /**
     * Creates a new article
     *
     * @param articleRequest the request containing the article's details
     *
     * @return a GlobalMessageResponse indicating the result of the operation
     */
    public GlobalMessageResponse createArticle(ArticleRequest articleRequest, User currentUser) {
        try {
            User author = userRepository.findById(currentUser.getId())
                    .orElseThrow(() -> new EntityNotFoundException("User not found : " + currentUser.getId()));

            Theme theme = themeRepository.findById(articleRequest.getThemeId())
                    .orElseThrow(() -> new EntityNotFoundException("Article not found with id: " + articleRequest.getThemeId()));

            Article article = articleMapper.toEntity(articleRequest);
            article.setAuthor(author);
            article.setTheme(theme);
            articleRepository.save(article);

            return new GlobalMessageResponse("Article created successfully");
        } catch (Exception ex) {
            return new GlobalMessageResponse(ex.getMessage());
        }
    }

    /**
     * Current User likes an Article
     *
     * @param id the id of the article
     * @param currentUser the currentUser
     *
     * @return a GlobalMessageResponse indicating the result of the operation
     */
    public GlobalMessageResponse likeArticle(Long id, User currentUser) {
            User user = userRepository.findById(currentUser.getId())
                    .orElseThrow(() -> new EntityNotFoundException("User not found : " + currentUser.getId()));

            Article article = articleRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Article not found with id: " + id));

            boolean userLiked = articleLikeRepository.existsByArticleIdAndUserId(article.getId(), currentUser.getId());

            if (userLiked) {
                throw new DataIntegrityViolationException("User already liked this article");
            }

            ArticleLike articleLike = ArticleLike.builder()
                                            .user(user)
                                            .article(article)
                                            .build();

            articleLikeRepository.save(articleLike);

            return new GlobalMessageResponse("You liked the article !");
    }

    /**
     * Retrieves a list of all available articles.
     *
     * @return an ArticleListResponse object with the list of articles
     */
    public ArticleListResponse getAllArticles(User currentUser) {
        return new ArticleListResponse(articleMapper.toResponseList(articleRepository.findAll(), currentUser));
    }


    /**
     * Retrieves the news feed for the given user
     *
     * @param currentUser The User object passed from controller
     * @param pageable Contains sorting information
     * @return An ArticleListResponse containing the sorted list of ArticleResponse DTOs.
     */
    @Transactional(readOnly = true)
    public ArticleListResponse getFeedForUser(User currentUser, Pageable pageable) {

        Set<Long> subscribedThemeIds = Collections.emptySet();
        List<ArticleResponse> feedArticleDTOs = Collections.emptyList();

        if (currentUser != null) {
            Optional<User> managedUserOpt = userRepository.findById(currentUser.getId());

            if (managedUserOpt.isPresent()) {
                User managedUser = managedUserOpt.get();
                if (managedUser.getSubscribedThemes() != null) {
                    subscribedThemeIds = managedUser.getSubscribedThemes().stream()
                            .map(Theme::getId)
                            .collect(Collectors.toSet());
                }
            }
        }

        if (!subscribedThemeIds.isEmpty()) {
            List<Article> articles = articleRepository.findByThemeIdIn(subscribedThemeIds, pageable);
            feedArticleDTOs = articleMapper.toResponseList(articles, currentUser);
        }

        return new ArticleListResponse(feedArticleDTOs);
    }

    /**
     * Retrieves a specific article by its ID.
     *
     * @param id the ID of the article to retrieve
     * @return an ArticleResponse object with the article's details
     * @throws IllegalArgumentException if the article is not found with the given ID
     */
    public ArticleResponse getArticle(Long id, User currentUser) {
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Article not found with id: " + id));
        return articleMapper.toResponse(article, currentUser);
    }
}
