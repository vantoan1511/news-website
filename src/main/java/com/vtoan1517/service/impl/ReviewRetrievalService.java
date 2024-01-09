package com.vtoan1517.service.impl;

import com.vtoan1517.dto.ReviewDTO;
import com.vtoan1517.entity.Review;
import com.vtoan1517.repository.ReviewRepository;
import com.vtoan1517.service.IReviewRetrievalService;
import com.vtoan1517.utils.CollectionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ReviewRetrievalService implements IReviewRetrievalService {

    private final ReviewRepository reviewRepository;

    private final CollectionMapper mapper;

    @Autowired
    public ReviewRetrievalService(ReviewRepository reviewRepository,
                                  CollectionMapper mapper) {
        this.reviewRepository = reviewRepository;
        this.mapper = mapper;
    }

    @Override
    public Page<ReviewDTO> findAllByArticleId(long id, Pageable pageable) {
        return reviewRepository.findAllByArticleId(id, pageable)
                .map(item -> mapper.map(item, ReviewDTO.class));
    }

    @Override
    public Page<ReviewDTO> findAllByArticleSlug(String slug, Pageable pageable) {
        return reviewRepository.findAllByArticleSlug(slug, pageable)
                .map(item -> mapper.map(item, ReviewDTO.class));
    }

    @Override
    public Page<ReviewDTO> findAllByUserId(long id, Pageable pageable) {
        return reviewRepository.findAllByUserId(id, pageable)
                .map(item -> mapper.map(item, ReviewDTO.class));
    }
}
