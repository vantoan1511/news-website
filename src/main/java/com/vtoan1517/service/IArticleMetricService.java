package com.vtoan1517.service;

public interface IArticleMetricService {
    long countByAuthor(String author);

    long countExceptTrash();
}
