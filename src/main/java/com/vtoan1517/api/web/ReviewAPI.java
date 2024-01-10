package com.vtoan1517.api.web;

import com.vtoan1517.dto.ErrorResponse;
import com.vtoan1517.dto.ReviewDTO;
import com.vtoan1517.dto.SuccessResponse;
import com.vtoan1517.exception.ArticleNotFoundException;
import com.vtoan1517.exception.ReviewNotFoundException;
import com.vtoan1517.exception.UserNotFoundException;
import com.vtoan1517.service.IReviewModificationService;
import com.vtoan1517.service.IReviewRetrievalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/reviews")
public class ReviewAPI {

    private final IReviewModificationService reviewModificationService;

    public ReviewAPI(IReviewModificationService reviewModificationService) {
        this.reviewModificationService = reviewModificationService;
    }

    @PostMapping
    public ResponseEntity<Object> create(@Valid @RequestBody ReviewDTO newReview) throws UserNotFoundException, ReviewNotFoundException, ArticleNotFoundException {
        return new ResponseEntity<>(reviewModificationService.save(newReview), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Object> update(@RequestBody ReviewDTO updatedReview) throws UserNotFoundException, ReviewNotFoundException, ArticleNotFoundException {
        return new ResponseEntity<>(reviewModificationService.save(updatedReview), HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<Object> deleteReviews(@RequestBody List<Long> ids) throws ReviewNotFoundException {
        try {
            reviewModificationService.deleteMultiple(ids);
            SuccessResponse response = SuccessResponse.builder()
                    .timestamp(new Date())
                    .status(HttpStatus.OK.value())
                    .message("Đã xóa thành công")
                    .build();
            return ResponseEntity.ok(response);
        } catch (ReviewNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ErrorResponse.builder()
                            .timestamp(new Date())
                            .status(HttpStatus.NOT_FOUND.value())
                            .message(ex.getMessage())
                            .build());
        }
    }
}
