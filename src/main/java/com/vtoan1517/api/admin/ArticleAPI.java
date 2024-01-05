package com.vtoan1517.api.admin;

import com.vtoan1517.dto.ArticleDTO;
import com.vtoan1517.dto.SuccessResponse;
import com.vtoan1517.exception.ArticleNotFoundException;
import com.vtoan1517.exception.MethodNotAllowException;
import com.vtoan1517.service.IArticleModificationService;
import com.vtoan1517.service.IArticleRetrievalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;

@RestController
@RequestMapping("/api/v1/articles")
public class ArticleAPI {

    private final IArticleRetrievalService retrievalService;

    private final IArticleModificationService modificationService;

    private final MessageSource messageSource;

    @Autowired
    public ArticleAPI(IArticleRetrievalService retrievalService, MessageSource messageSource, IArticleModificationService modificationService) {
        this.retrievalService = retrievalService;
        this.messageSource = messageSource;
        this.modificationService = modificationService;
    }

    @GetMapping("/{slug}")
    public ResponseEntity<ArticleDTO> get(@PathVariable("slug") String slug) throws ArticleNotFoundException {
        return new ResponseEntity<>(retrievalService.findBySlug(slug), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ArticleDTO> createArticle(@Valid @RequestBody ArticleDTO articleDTO) throws MethodNotAllowException {
        return new ResponseEntity<>(modificationService.save(articleDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}/{action}")
    public ResponseEntity<Object> handleAction(@PathVariable("id") long id,
                                               @PathVariable("action") String action) throws ArticleNotFoundException {
        switch (action) {
            case "publish" -> modificationService.publish(id);
            case "approve" -> modificationService.approve(id);
            case "refuse" -> modificationService.refuse(id);
            case "unpublish" -> modificationService.reject(id);
            case "restore" -> modificationService.restore(id);
            case "trash" -> modificationService.trash(id);
            default -> throw new IllegalArgumentException("Invalid action: " + action);
        }

        return new ResponseEntity<>(SuccessResponse.builder()
                .timestamp(new Date())
                .status(HttpStatus.OK.value())
                .build(), HttpStatus.OK);
    }

    @PutMapping("/trash")
    public ResponseEntity<Object> trash(@RequestBody long[] ids) {
        modificationService.trash(ids);
        SuccessResponse successResponse = SuccessResponse.builder()
                .timestamp(new Date())
                .status(HttpStatus.OK.value())
                .message(messageSource.getMessage("article.trash.success", null, null))
                .build();
        return new ResponseEntity<>(successResponse, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<ArticleDTO> updateArticle(@Valid @RequestBody ArticleDTO articleDTO) throws MethodNotAllowException {
        return new ResponseEntity<>(modificationService.save(articleDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteSingle(@PathVariable("id") long id) throws ArticleNotFoundException, MethodNotAllowException {
        modificationService.delete(id);
        SuccessResponse responseBody = SuccessResponse.builder()
                .timestamp(new Date())
                .status(HttpStatus.NO_CONTENT.value())
                .message(messageSource.getMessage("article.delete.success", null, null))
                .build();
        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Object> delete(@RequestBody long[] ids) throws ArticleNotFoundException, MethodNotAllowException {
        modificationService.delete(ids);
        SuccessResponse responseBody = SuccessResponse.builder()
                .timestamp(new Date())
                .status(HttpStatus.NO_CONTENT.value())
                .message(messageSource.getMessage("article.delete.success", null, null))
                .build();
        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }
}
