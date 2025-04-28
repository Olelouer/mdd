package com.openclassrooms.mddapi.mapper;

import com.openclassrooms.mddapi.dto.*;
import com.openclassrooms.mddapi.model.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.stream.Collectors;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CommentMapper {
    private final UserMapper userMapper;

    public Comment toEntity(CommentRequest commentRequest) {
        return Comment.builder()
                .content(commentRequest.getContent())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }

    public CommentResponse toResponse(Comment comment) {
        UserResponse userResponse = null;

        if (comment.getAuthor() != null) {
            userResponse = userMapper.toResponse(comment.getAuthor());
        }

        return CommentResponse.builder()
                .id(comment.getId())
                .content(comment.getContent())
                .createdAt(comment.getCreatedAt())
                .updatedAt(comment.getUpdatedAt())
                .author(userResponse)
                .build();
    }

    public List<CommentResponse> toResponseList (List<Comment> comments) {
        return comments.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }
}
