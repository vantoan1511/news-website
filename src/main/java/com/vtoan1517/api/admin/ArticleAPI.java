package com.vtoan1517.api.admin;

import com.vtoan1517.dto.ArticleDTO;
import com.vtoan1517.dto.SuccessResponse;
import com.vtoan1517.exception.ArticleNotFoundException;
import com.vtoan1517.exception.MethodNotAllowException;
import com.vtoan1517.service.IArticleModificationService;
import com.vtoan1517.service.IArticleRetrievalService;
import com.vtoan1517.service.IReviewRetrievalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;

@RestController
@RequestMapping("/api/v1/articles")
public class ArticleAPI {

    private final IReviewRetrievalService reviewRetrievalService;

    private final IArticleRetrievalService articleRetrievalService;

    private final IArticleModificationService articleModificationService;

    private final MessageSource messageSource;

    @Autowired
    public ArticleAPI(IArticleRetrievalService articleRetrievalService, MessageSource messageSource, IArticleModificationService articleModificationService, IReviewRetrievalService reviewRetrievalService) {
        this.articleRetrievalService = articleRetrievalService;
        this.messageSource = messageSource;
        this.articleModificationService = articleModificationService;
        this.reviewRetrievalService = reviewRetrievalService;
    }

    @GetMapping("/{slug}")
    public ResponseEntity<ArticleDTO> get(@PathVariable("slug") String slug) throws ArticleNotFoundException {
        return new ResponseEntity<>(articleRetrievalService.findBySlug(slug), HttpStatus.OK);
    }

    @GetMapping("/{id}/reviews")
    public ResponseEntity<Object> getReviews(@PathVariable("id") long id,
                                             @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                             @RequestParam(value = "limit", required = false, defaultValue = "2") int limit,
                                             @RequestParam(value = "order", required = false, defaultValue = "desc") String order,
                                             @RequestParam(value = "by", required = false, defaultValue = "createdDate") String by) {
        Sort.Direction direction = order.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
        Pageable pageable = new PageRequest(page - 1, limit, new Sort(direction, by));
        return new ResponseEntity<>(reviewRetrievalService.findAllByArticleId(id, pageable), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ArticleDTO> createArticle(@Valid @RequestBody ArticleDTO articleDTO) throws MethodNotAllowException {
        return new ResponseEntity<>(articleModificationService.save(articleDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}/{action}")
    public ResponseEntity<Object> handleAction(@PathVariable("id") long id,
                                               @PathVariable("action") String action) throws ArticleNotFoundException {
        switch (action) {
            case "publish" -> articleModificationService.publish(id);
            case "approve" -> articleModificationService.approve(id);
            case "refuse" -> articleModificationService.refuse(id);
            case "unpublish" -> articleModificationService.reject(id);
            case "restore" -> articleModificationService.restore(id);
            case "trash" -> articleModificationService.trash(id);
            default -> throw new IllegalArgumentException("Invalid action: " + action);
        }

        return new ResponseEntity<>(SuccessResponse.builder()
                .timestamp(new Date())
                .status(HttpStatus.OK.value())
                .build(), HttpStatus.OK);
    }

    @PutMapping("/trash")
    public ResponseEntity<Object> trash(@RequestBody long[] ids) {
        articleModificationService.trash(ids);
        SuccessResponse successResponse = SuccessResponse.builder()
                .timestamp(new Date())
                .status(HttpStatus.OK.value())
                .message(messageSource.getMessage("article.trash.success", null, null))
                .build();
        return new ResponseEntity<>(successResponse, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<ArticleDTO> updateArticle(@Valid @RequestBody ArticleDTO articleDTO) throws MethodNotAllowException {
        return new ResponseEntity<>(articleModificationService.save(articleDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteSingle(@PathVariable("id") long id) throws ArticleNotFoundException, MethodNotAllowException {
        articleModificationService.delete(id);
        SuccessResponse responseBody = SuccessResponse.builder()
                .timestamp(new Date())
                .status(HttpStatus.NO_CONTENT.value())
                .message(messageSource.getMessage("article.delete.success", null, null))
                .build();
        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Object> delete(@RequestBody long[] ids) throws ArticleNotFoundException, MethodNotAllowException {
        articleModificationService.delete(ids);
        SuccessResponse responseBody = SuccessResponse.builder()
                .timestamp(new Date())
                .status(HttpStatus.NO_CONTENT.value())
                .message(messageSource.getMessage("article.delete.success", null, null))
                .build();
        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }
}
