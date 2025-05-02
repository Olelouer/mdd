package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.dto.ArticleListResponse;
import com.openclassrooms.mddapi.dto.ArticleRequest;
import com.openclassrooms.mddapi.dto.ArticleResponse;
import com.openclassrooms.mddapi.dto.GlobalMessageResponse;
import com.openclassrooms.mddapi.model.User;
import com.openclassrooms.mddapi.service.ArticleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/articles")
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleService articleService;

    /**
     * Creates a new article based on the provided ArticleRequest.
     *
     * @param articleRequest the request containing the article's details
     * @return a ResponseEntity containing a GlobalMessageResponse indicating the result of the operation
     */
    @PostMapping
    public ResponseEntity<GlobalMessageResponse> createArticle(@Valid @RequestBody ArticleRequest articleRequest) {
        return ResponseEntity.ok(articleService.createArticle(articleRequest));
    }

    /**
     * Retrieves a list of all available articles.
     *
     * @return a ResponseEntity containing an ArticleListResponse object with the list of articles
     */
    @GetMapping
    public ResponseEntity<ArticleListResponse> getAllArticles() {
        return ResponseEntity.ok(articleService.getAllArticles());
    }

    /**
     * Retrieves the personalized news feed for the currently authenticated user.
     *
     * @return ArticleListResponse object with the list of articles
     */
    @GetMapping("/feed")
    public ResponseEntity<ArticleListResponse> getUserFeed(
            @AuthenticationPrincipal User currentUser,
            @PageableDefault(
                    size = Integer.MAX_VALUE, // Effectively disable pagination by fetching up to max results
                    sort = "createdAt",
                    direction = Sort.Direction.DESC // Default sort if none provided by client
            ) Pageable pageable
    ) {
        return ResponseEntity.ok(articleService.getFeedForUser(currentUser, pageable));
    }

    /**
     * Retrieves a specific article by its ID.
     *
     * @param id the ID of the article to retrieve
     * @return a ResponseEntity containing an ArticleResponse object with the article's details
     */
    @GetMapping("/{id}")
    public ResponseEntity<ArticleResponse> getArticle(@PathVariable Long id) {
        return ResponseEntity.ok(articleService.getArticle(id));
    }
}
