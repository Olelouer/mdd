package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.dto.CommentRequest;
import com.openclassrooms.mddapi.dto.GlobalMessageResponse;
import com.openclassrooms.mddapi.mapper.CommentMapper;
import com.openclassrooms.mddapi.model.Article;
import com.openclassrooms.mddapi.model.Comment;
import com.openclassrooms.mddapi.model.User;
import com.openclassrooms.mddapi.repository.CommentRepository;
import com.openclassrooms.mddapi.repository.ArticleRepository;
import com.openclassrooms.mddapi.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommentService {
    private final UserRepository userRepository;
    private final ArticleRepository articleRepository;
    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;

    /**
     * Create a new comment
     *
     * @param commentRequest the request containing the comment's details
     * @return GlobaleMessageResponse
     */
    public GlobalMessageResponse createComment(CommentRequest commentRequest, User currentUser) {
        try {
            User author = userRepository.findById(currentUser.getId())
                    .orElseThrow(() -> new EntityNotFoundException("User not found : " + currentUser.getId()));

            Article article = articleRepository.findById(commentRequest.getArticleId())
                    .orElseThrow(() -> new EntityNotFoundException("Article not found with id: " + commentRequest.getArticleId()));

            Comment comment = commentMapper.toEntity(commentRequest);
            comment.setAuthor(author);
            comment.setArticle(article);
            commentRepository.save(comment);

            return new GlobalMessageResponse("Comment created successfully");
        } catch (Exception ex) {
            log.error("Unexpected error while creating comment: {}", ex.getMessage(), ex);
            return new GlobalMessageResponse(ex.getMessage());
        }
    }
}
