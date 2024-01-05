package com.vtoan1517.service;

import com.vtoan1517.dto.ArticleDTO;
import com.vtoan1517.exception.ArticleNotFoundException;
import com.vtoan1517.exception.MethodNotAllowException;

public interface IArticleModificationService {

    ArticleDTO save(ArticleDTO articleDTO) throws MethodNotAllowException;

    void publish(long id) throws ArticleNotFoundException;

    void approve(long id) throws ArticleNotFoundException;

    void refuse(long id) throws ArticleNotFoundException;

    void reject(long id) throws ArticleNotFoundException;

    void restore(long id) throws ArticleNotFoundException;

    void trash(long id) throws ArticleNotFoundException;

    void trash(long[] ids);

    void delete(long[] ids) throws ArticleNotFoundException, MethodNotAllowException;

    void delete(long id) throws ArticleNotFoundException, MethodNotAllowException;
}
