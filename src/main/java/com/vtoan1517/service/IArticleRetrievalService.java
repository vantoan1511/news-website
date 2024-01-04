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

    List<ArticleDTO> findAllByAuthor(String author, Pageable pageable);

    List<ArticleDTO> findAllByFeaturedAndAuthor(boolean featured, String author, Pageable pageable);

    List<ArticleDTO> findAllByStatusCode(String statusCode, Pageable pageable);

    List<ArticleDTO> findAllByStatusCodeAndAuthor(String statusCode, String author, Pageable pageable);

    List<ArticleDTO> findAllByFeatured(boolean featured, Pageable pageable);

    List<ArticleDTO> findAllByStatusCodeAndFeatured(String statusCode, boolean featured, Pageable pageable);

    ArticleDTO findById(long id) throws ArticleNotFoundException;

    ArticleDTO findBySlug(String slug) throws ArticleNotFoundException;

    ArticleDTO findBySlugAndStatus(String slug, String status) throws ArticleNotFoundException;
}
