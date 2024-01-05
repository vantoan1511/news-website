package com.vtoan1517.service;

import com.vtoan1517.dto.ReviewDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IReviewRetrievalService {
    Page<ReviewDTO> findAllByArticleId(long id, Pageable pageable);
}
