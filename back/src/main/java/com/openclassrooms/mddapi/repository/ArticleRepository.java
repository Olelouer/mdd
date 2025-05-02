package com.openclassrooms.mddapi.repository;

import com.openclassrooms.mddapi.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
    /**
     * Finds articles whose theme ID is in the provided collection, using the sorting
     *
     * @param themeIds A collection of theme IDs to filter by.
     * @param pageable Contains sorting information.
     * @return A List of articles matching the criteria, sorted accordingly.
     */
    List<Article> findByThemeIdIn(Collection<Long> themeIds, Pageable pageable);

}
