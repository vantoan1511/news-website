package com.vtoan1517.repository;

import com.vtoan1517.entity.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {

    Page<Article> findAll(Specification<Article> spec, Pageable pageable);

    Page<Article> findAll(Pageable pageable);

    Page<Article> findAllByTitleContainingIgnoreCase(String keyword, Pageable pageable);

    Page<Article> findAllByDescriptionContainingIgnoreCase(String keyword, Pageable pageable);

    Page<Article> findAllByContentIsContainingIgnoreCase(String keyword, Pageable pageable);

    List<Article> findAllByStatusCodeAndCategoryCode(String statusCode, String categoryCode, Pageable pageable);


    Page<Article> findAllByCreatedByOrAccessCode(String createdBy, String statusCode, Pageable pageable);

    List<Article> findAllByCreatedBy(String createdBy, Pageable pageable);

    Page<Article> findAllByFeaturedAndCreatedByOrFeaturedAndAccessCode(boolean featured, String createdBy, boolean oFeatured, String accessCode, Pageable pageable);

    Page<Article> findAllByStatusCode(String statusCode, Pageable pageable);

    Page<Article> findAllByStatusCodeAndCreatedByOrStatusCodeAndAccessCode(String statusCode, String createdBy, String oStatusCode, String accessCode, Pageable pageable);

    Page<Article> findAllByFeatured(boolean featured, Pageable pageable);

    List<Article> findAllByStatusCodeAndFeatured(String statusCode, boolean featured, Pageable pageable);

    List<Article> findByTitle(@Param("title") String title, Pageable pageable);

    Article findById(long id);

    Article findBySlug(String slug);

    Article findBySlugAndStatusCode(String slug, String statusCode);

    long countAllByStatusCodeNot(String statusCode);

    long countAllByCreatedByAndStatusCodeNotOrAccessCode(String createdBy, String statusCode, String accessCode);
}
