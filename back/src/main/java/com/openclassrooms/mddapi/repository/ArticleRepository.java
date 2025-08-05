package com.openclassrooms.mddapi.repository;

import com.openclassrooms.mddapi.model.Article;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
    /**
     * Finds articles whose theme ID is in the provided collection, using the sorting
     *
     * @param themeIds A collection of theme IDs to filter by.
     * @param pageable Contains sorting information.
     * @return A List of articles matching the criteria, sorted accordingly.
     */
    List<Article> findByAssociatedThemes_IdIn(Collection<Long> themeIds, Pageable pageable);

    @Override
    @EntityGraph(value = "Article.withThemes")
    Optional<Article> findById(Long id);
}
