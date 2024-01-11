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
import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    public ReviewDTO save(ReviewDTO reviewDTO) throws ArticleNotFoundException, UserNotFoundException, ReviewNotFoundException {
        Review review = new Review();
        review.setText(StringEscapeUtils.escapeHtml4(reviewDTO.getText()));

        Article article = articleRepository.findBySlug(reviewDTO.getArticleSlug());
        User user = userRepository.findByUsername(reviewDTO.getUsername());
        //Review review = mapper.map(reviewDTO, Review.class);

        if (article == null) throw new ArticleNotFoundException("Bài viết không tồn tại hoặc đã bị xóa");
        if (user == null) throw new UserNotFoundException("Người dùng không tồn tại");
        if (reviewDTO.getParentId() != 0) {
            Review root = reviewRepository.findOne(reviewDTO.getParentId());
            if (root == null) throw new ReviewNotFoundException("Bình luận không tồn tại hoặc đã bị xóa");
            review.setParent(root);
        }

        review.setUser(user);
        review.setArticle(article);

        if (reviewDTO.getId() != 0) {
            Review oldReview = reviewRepository.findOne(reviewDTO.getId());
            if (oldReview == null) throw new ReviewNotFoundException("Bình luận không tồn tại hoặc đã bị xóa");
            review.setId(reviewDTO.getId());
            review.setCreatedBy(oldReview.getCreatedBy());
            review.setCreatedDate(oldReview.getCreatedDate());
            review.setParent(oldReview.getParent());
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
    public void deleteMultiple(List<Long> ids) throws ReviewNotFoundException {
        for (long id : ids) {
            this.delete(id);
        }
    }
}
