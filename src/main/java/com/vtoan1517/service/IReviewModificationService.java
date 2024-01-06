package com.vtoan1517.service;

import com.vtoan1517.dto.ReviewDTO;
import com.vtoan1517.exception.ArticleNotFoundException;
import com.vtoan1517.exception.ReviewNotFoundException;
import com.vtoan1517.exception.UserNotFoundException;

public interface IReviewModificationService {
    ReviewDTO save(ReviewDTO newReviewDTO) throws ArticleNotFoundException, UserNotFoundException, ReviewNotFoundException;

    void delete(long id) throws ReviewNotFoundException;

    void deleteMultiple(long[] ids) throws ReviewNotFoundException;
}
