package com.openclassrooms.mddapi.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "themes")
public class Theme {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title cannot be empty")
    @NotNull(message = "Title cannot be null")
    @Column(nullable = false)
    private String title;

    @NotBlank(message = "Description cannot be empty")
    @NotNull(message = "Description cannot be null")
    @Column(nullable = false)
    private String description;

    @OneToMany(mappedBy = "theme", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Article> articles = new ArrayList<>();

    @ManyToMany(mappedBy = "subscribedThemes")
    private List<User> subscribers = new ArrayList<>();

    @Column(name= "created_at", nullable = false)
    @JsonProperty("created_at")
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    private LocalDateTime createdAt;

    @Column(name= "updated_at", nullable = false)
    @JsonProperty("updated_at")
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    private LocalDateTime updatedAt;

    // Helpers to maintain bidirectional consistency.

    public void addArticle(Article article) {
        articles.add(article);
        article.setTheme(this);
    }

    public void removeArticle(Article article) {
        articles.remove(article);
        article.setTheme(null);
    }
}
