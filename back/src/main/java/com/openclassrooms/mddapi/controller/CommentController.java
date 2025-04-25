package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.dto.CommentRequest;
import com.openclassrooms.mddapi.dto.GlobalMessageResponse;
import com.openclassrooms.mddapi.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<GlobalMessageResponse> createComment(@Valid @RequestBody CommentRequest commentRequest) {
        return ResponseEntity.ok(commentService.createComment(commentRequest));
    }
}
