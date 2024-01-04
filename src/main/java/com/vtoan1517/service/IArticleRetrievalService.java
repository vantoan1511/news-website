package com.vtoan1517.service;

import com.vtoan1517.dto.ArticleDTO;
import com.vtoan1517.exception.ArticleNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IArticleRetrievalService {

    Page<ArticleDTO> findAll(Pageable pageable);

    Page<ArticleDTO> findAllByKeyword(String keyword, Pageable pageable);

    List<ArticleDTO> findAllByCategoryCode(String code, Pageable pageable);

    Page<ArticleDTO> findAllByAuthorOrPublicAccess(String authorName, Pageable pageable);

    Page<ArticleDTO> findAllByFeaturedAndAuthor(boolean featured, String authorName, Pageable pageable);

    Page<ArticleDTO> findAllByStatusCode(String statusCode, Pageable pageable);

    Page<ArticleDTO> findAllByStatusCodeAndAuthor(String statusCode, String authorName, Pageable pageable);

    Page<ArticleDTO> findAllByFeatured(boolean featured, Pageable pageable);

    List<ArticleDTO> findAllByStatusCodeAndFeatured(String statusCode, boolean featured, Pageable pageable);

    ArticleDTO findById(long id) throws ArticleNotFoundException;

    ArticleDTO findBySlug(String slug) throws ArticleNotFoundException;

    ArticleDTO findBySlugAndStatus(String slug, String status) throws ArticleNotFoundException;
}
