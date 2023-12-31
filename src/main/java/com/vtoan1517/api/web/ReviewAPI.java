package com.vtoan1517.api.web;

import com.vtoan1517.dto.ReviewDTO;
import com.vtoan1517.dto.SuccessResponse;
import com.vtoan1517.exception.ArticleNotFoundException;
import com.vtoan1517.exception.ReviewNotFoundException;
import com.vtoan1517.exception.UserNotFoundException;
import com.vtoan1517.service.IReviewModificationService;
import com.vtoan1517.service.IReviewRetrievalService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;

@RestController
@RequestMapping("/api/v1/reviews")
public class ReviewAPI {

    private final IReviewModificationService reviewModificationService;
    private final IReviewRetrievalService reviewRetrievalService;

    public ReviewAPI(IReviewModificationService reviewModificationService,
                     IReviewRetrievalService reviewRetrievalService) {
        this.reviewModificationService = reviewModificationService;
        this.reviewRetrievalService = reviewRetrievalService;
    }

    @PostMapping
    public ResponseEntity<Object> create(@Valid @RequestBody ReviewDTO newReview) throws UserNotFoundException, ReviewNotFoundException, ArticleNotFoundException {
        return new ResponseEntity<>(reviewModificationService.save(newReview), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Object> update(@Valid @RequestBody ReviewDTO updatedReview) throws UserNotFoundException, ReviewNotFoundException, ArticleNotFoundException {
        return new ResponseEntity<>(reviewModificationService.save(updatedReview), HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<Object> update(@RequestBody long[] ids) throws ReviewNotFoundException {
        reviewModificationService.deleteMultiple(ids);
        SuccessResponse response = SuccessResponse.builder()
                .timestamp(new Date())
                .status(HttpStatus.OK.value())
                .message("Đã xóa thành công")
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
