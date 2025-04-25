package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.dto.ArticleListResponse;
import com.openclassrooms.mddapi.dto.ArticleRequest;
import com.openclassrooms.mddapi.dto.ArticleResponse;
import com.openclassrooms.mddapi.dto.GlobalMessageResponse;
import com.openclassrooms.mddapi.service.ArticleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/articles")
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleService articleService;

    /**
     * Creates a new article
     *
     * @param articleRequest
     *
     * @return GlobalMessageResponse
     */
    /*@PostMapping
    public ResponseEntity<GlobalMessageResponse> createArticle(@Valid @RequestBody ArticleRequest articleRequest) {
        return ResponseEntity.ok(articleService.createArticle(articleRequest));
    }*/

    /**
     * Gets all articles
     *
     * @return List of articles
     */
    @GetMapping
    public ResponseEntity<ArticleListResponse> getAllArticles() {
        return ResponseEntity.ok(articleService.getAllArticles());
    }

    /**
     * Gets a single article
     *
     * @param id
     *
     * @return Article
     */
    @GetMapping("/{id}")
    public ResponseEntity<ArticleResponse> getArticle(@PathVariable Long id) {
        return ResponseEntity.ok(articleService.getArticle(id));
    }
}
