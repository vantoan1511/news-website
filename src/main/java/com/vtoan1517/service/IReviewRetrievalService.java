package com.vtoan1517.service;

import com.vtoan1517.dto.ReviewDTO;
import com.vtoan1517.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IReviewRetrievalService {
    Page<ReviewDTO> findAllByArticleId(long id, Pageable pageable);

    Page<ReviewDTO> findAllByUserId(long id, Pageable pageable);

}
