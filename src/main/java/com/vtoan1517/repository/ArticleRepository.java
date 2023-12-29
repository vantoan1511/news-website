package com.vtoan1517.repository;

import com.vtoan1517.entity.ArticleEntity;
import com.vtoan1517.entity.StatusEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleRepository extends JpaRepository<ArticleEntity, Long> {

    List<ArticleEntity> findAllByStatusCodeNot(String statusCode, Pageable pageable);

    List<ArticleEntity> findAllByStatusCodeNotAndCreatedBy(String statusCode, String createdBy, Pageable pageable);

    List<ArticleEntity> findAllByCreatedBy(String createdBy, Pageable pageable);

    List<ArticleEntity> findAllByFeaturedAndCreatedBy(boolean featured, String createdBy, Pageable pageable);

    List<ArticleEntity> findAllByStatusCode(String statusCode, Pageable pageable);

    List<ArticleEntity> findAllByStatusCodeAndCreatedBy(String statusCode, String createdBy, Pageable pageable);

    List<ArticleEntity> findAllByFeatured(boolean featured, Pageable pageable);

    ArticleEntity findById(long id);

    ArticleEntity findBySlug(String slug);

    ArticleEntity findBySlugAndStatusCode(String slug, String statusCode);

    long countAllByStatusCodeNot(String statusCode);

    long countAllByCreatedByAndStatusCodeNotOrAccessCode(String createdBy, String statusCode, String accessCode);
}
