package com.vtoan1517.service.impl;

import com.vtoan1517.dto.ReviewDTO;
import com.vtoan1517.entity.Article;
import com.vtoan1517.entity.Review;
import com.vtoan1517.entity.User;
import com.vtoan1517.repository.ArticleRepository;
import com.vtoan1517.repository.ReviewRepository;
import com.vtoan1517.repository.UserRepository;
import com.vtoan1517.service.IArticleRetrievalService;
import com.vtoan1517.service.IReviewModificationService;
import com.vtoan1517.service.IUserService;
import com.vtoan1517.utils.CollectionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewModificationService implements IReviewModificationService {
    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;
    private final ReviewRepository reviewRepository;

    private final CollectionMapper mapper;

    @Autowired
    public ReviewModificationService(ReviewRepository reviewRepository, CollectionMapper mapper, ArticleRepository articleRepository, UserRepository userRepository) {
        this.reviewRepository = reviewRepository;
        this.mapper = mapper;
        this.articleRepository = articleRepository;
        this.userRepository = userRepository;
    }

    @Override
    public ReviewDTO save(ReviewDTO newReview) {
        Article article = articleRepository.findOne(newReview.getArticle().getId());
        User user = userRepository.getOne(newReview.getUser().getId());
        Review parent = reviewRepository.findOne(newReview.getParent().getId());

        Review review = mapper.map(newReview, Review.class);

        review.setUser(user);
        review.setArticle(article);

        if (newReview.getParent().getId() != 0) {
            review.setParent(parent);
        }

        if (newReview.getId() != 0) {
            Review oldReview = reviewRepository.findOne(newReview.getId());
            review.setCreatedBy(oldReview.getCreatedBy());
            review.setCreatedDate(oldReview.getCreatedDate());
        }

        return null;
    }
}
