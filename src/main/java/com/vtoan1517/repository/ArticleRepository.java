package com.vtoan1517.repository;

import com.vtoan1517.entity.Article;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {


    List<Article> findAllByStatusCodeNot(String statusCode, Pageable pageable);

    List<Article> findAllByStatusCodeNotAndCreatedBy(String statusCode, String createdBy, Pageable pageable);

    List<Article> findAllByCreatedBy(String createdBy, Pageable pageable);

    List<Article> findAllByFeaturedAndCreatedBy(boolean featured, String createdBy, Pageable pageable);

    List<Article> findAllByStatusCode(String statusCode, Pageable pageable);

    List<Article> findAllByStatusCodeAndCreatedBy(String statusCode, String createdBy, Pageable pageable);

    List<Article> findAllByFeatured(boolean featured, Pageable pageable);

    List<Article> findAllByStatusCodeAndFeatured(String statusCode, boolean featured, Pageable pageable);

    Article findById(long id);

    Article findBySlug(String slug);

    Article findBySlugAndStatusCode(String slug, String statusCode);

    long countAllByStatusCodeNot(String statusCode);

    long countAllByCreatedByAndStatusCodeNotOrAccessCode(String createdBy, String statusCode, String accessCode);
}
