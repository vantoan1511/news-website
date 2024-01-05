package com.vtoan1517.service.impl;

import com.vtoan1517.dto.ReviewDTO;
import com.vtoan1517.service.IReviewRetrievalService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class ReviewRetrievalService implements IReviewRetrievalService {
    @Override
    public Page<ReviewDTO> findAllByArticleId(long id, Pageable pageable) {
        return null;
    }
}
