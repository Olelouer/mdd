package com.openclassrooms.mddapi.mapper;

import com.openclassrooms.mddapi.dto.CommentRequest;
import com.openclassrooms.mddapi.model.Comment;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class CommentMapper {
    public Comment toEntity(CommentRequest commentRequest) {
        return Comment.builder()
                .content(commentRequest.getContent())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }

}
