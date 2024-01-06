package com.vtoan1517.service.impl;

import com.vtoan1517.dto.ReviewDTO;
import com.vtoan1517.entity.Article;
import com.vtoan1517.entity.Review;
import com.vtoan1517.entity.User;
import com.vtoan1517.exception.ArticleNotFoundException;
import com.vtoan1517.exception.ReviewNotFoundException;
import com.vtoan1517.exception.UserNotFoundException;
import com.vtoan1517.repository.ArticleRepository;
import com.vtoan1517.repository.ReviewRepository;
import com.vtoan1517.repository.UserRepository;
import com.vtoan1517.service.IReviewModificationService;
import com.vtoan1517.utils.CollectionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReviewModificationService implements IReviewModificationService {
    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;
    private final ReviewRepository reviewRepository;

    private final CollectionMapper mapper;

    @Autowired
    public ReviewModificationService(ReviewRepository reviewRepository,
                                     CollectionMapper mapper,
                                     ArticleRepository articleRepository,
                                     UserRepository userRepository) {
        this.reviewRepository = reviewRepository;
        this.mapper = mapper;
        this.articleRepository = articleRepository;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public ReviewDTO save(ReviewDTO newReviewDTO) throws ArticleNotFoundException, UserNotFoundException, ReviewNotFoundException {
        Article article = articleRepository.findById(newReviewDTO.getArticleId());
        User user = userRepository.findOne(newReviewDTO.getUserId());
        Review review = mapper.map(newReviewDTO, Review.class);

        if (article == null) throw new ArticleNotFoundException("Bài viết không tồn tại hoặc đã bị xóa");
        if (user == null) throw new UserNotFoundException("Người dùng không tồn tại");
        if (newReviewDTO.getParentId() != 0) {
            Review parent = reviewRepository.findOne(newReviewDTO.getParentId());
            if (parent == null) throw new ReviewNotFoundException("Bình luận không tồn tại hoặc đã bị xóa");
            review.setParent(parent);
        }

        review.setUser(user);
        review.setArticle(article);

        if (newReviewDTO.getId() != 0) {
            Review oldReview = reviewRepository.findOne(newReviewDTO.getId());
            if (oldReview == null) throw new ReviewNotFoundException("Bình luận không tồn tại hoặc đã bị xóa");
            review.setCreatedBy(oldReview.getCreatedBy());
            review.setCreatedDate(oldReview.getCreatedDate());
        }

        return mapper.map(reviewRepository.save(review), ReviewDTO.class);
    }

    @Override
    @Transactional
    public void delete(long id) throws ReviewNotFoundException {
        if (id <= 0 || reviewRepository.findOne(id) == null)
            throw new ReviewNotFoundException("Bình luận không tồn tại hoặc đã bị xóa");
        reviewRepository.delete(id);
    }

    @Override
    public void deleteMultiple(long[] ids) throws ReviewNotFoundException {
        for (long id : ids) {
            this.delete(id);
        }
    }
}
