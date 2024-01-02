package com.vtoan1517.service;

import com.vtoan1517.dto.ArticleDTO;
import com.vtoan1517.exception.ArticleNotFoundException;
import com.vtoan1517.exception.MethodNotAllowException;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IArticleService {

    List<ArticleDTO> findAll();

    List<ArticleDTO> findAll(Pageable pageable);

    List<ArticleDTO> findAllByAuthor(String author, Pageable pageable);

    List<ArticleDTO> findAllByFeaturedAndAuthor(boolean featured, String author, Pageable pageable);

    List<ArticleDTO> findAllByStatusCode(String statusCode, Pageable pageable);

    List<ArticleDTO> findAllByStatusCodeAndAuthor(String statusCode, String author, Pageable pageable);

    List<ArticleDTO> findAllByFeatured(boolean featured, Pageable pageable);

    List<ArticleDTO> findAllByStatusCodeAndFeatured(String statusCode, boolean featured, Pageable pageable);

    ArticleDTO findById(long id) throws ArticleNotFoundException;

    ArticleDTO findBySlug(String slug) throws ArticleNotFoundException;

    ArticleDTO findBySlugAndStatus(String slug, String status) throws ArticleNotFoundException;

    ArticleDTO save(ArticleDTO articleDTO);

    ArticleDTO publish(long id);

    ArticleDTO approve(long id);

    ArticleDTO refuse(long id);

    ArticleDTO unpublish(long id) throws ArticleNotFoundException;

    ArticleDTO restore(long id);

    boolean isUniqueSlug(String slug);

    ArticleDTO trash(long id) throws ArticleNotFoundException;

    void trash(long[] ids);

    void delete(long[] ids) throws ArticleNotFoundException, MethodNotAllowException;

    void delete(long id) throws ArticleNotFoundException, MethodNotAllowException;

    long getTotalItems(String author);

    long getTotalItemsExceptTrash();

}
