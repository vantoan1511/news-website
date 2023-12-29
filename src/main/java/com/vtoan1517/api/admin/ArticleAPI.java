package com.vtoan1517.api.admin;

import com.vtoan1517.dto.ArticleDTO;
import com.vtoan1517.dto.SuccessResponse;
import com.vtoan1517.exception.ArticleNotFoundException;
import com.vtoan1517.exception.MethodNotAllowException;
import com.vtoan1517.service.IArticleService;
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

    private final IArticleService articleService;

    private final MessageSource messageSource;

    @Autowired
    public ArticleAPI(IArticleService articleService, MessageSource messageSource) {
        this.articleService = articleService;
        this.messageSource = messageSource;
    }

    @GetMapping("/{slug}")
    public ResponseEntity<ArticleDTO> get(@PathVariable("slug") String slug) throws ArticleNotFoundException {
        return new ResponseEntity<>(articleService.findBySlug(slug), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ArticleDTO> createArticle(@Valid @RequestBody ArticleDTO articleDTO) {
        return new ResponseEntity<>(articleService.save(articleDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}/{action}")
    public ResponseEntity<ArticleDTO> handleAction(
            @PathVariable("id") long id,
            @PathVariable("action") String action
    ) throws ArticleNotFoundException {
        ArticleDTO articleDTO = switch (action) {
            case "publish" -> articleService.publish(id);
            case "approve" -> articleService.approve(id);
            case "refuse" -> articleService.refuse(id);
            case "unpublish" -> articleService.unpublish(id);
            case "restore" -> articleService.restore(id);
            case "trash" -> articleService.trash(id);
            default -> throw new IllegalArgumentException("Invalid action: " + action);
        };

        return new ResponseEntity<>(articleDTO, HttpStatus.OK);
    }

    @PutMapping("/trash")
    public ResponseEntity<Object> trash(@RequestBody long[] ids) {
        articleService.trash(ids);
        SuccessResponse successResponse = SuccessResponse.builder()
                .timestamp(new Date())
                .status(HttpStatus.OK.value())
                .message(messageSource.getMessage("article.trash.success", null, null))
                .build();
        return new ResponseEntity<>(successResponse, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<ArticleDTO> updateArticle(@Valid @RequestBody ArticleDTO articleDTO) {
        return new ResponseEntity<>(articleService.save(articleDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteSingle(@PathVariable("id") long id) throws ArticleNotFoundException, MethodNotAllowException {
        articleService.delete(id);
        SuccessResponse responseBody = SuccessResponse.builder()
                .timestamp(new Date())
                .status(HttpStatus.NO_CONTENT.value())
                .message(messageSource.getMessage("article.delete.success", null, null))
                .build();
        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Object> delete(@RequestBody long[] ids) throws ArticleNotFoundException, MethodNotAllowException {
        articleService.delete(ids);
        SuccessResponse responseBody = SuccessResponse.builder()
                .timestamp(new Date())
                .status(HttpStatus.NO_CONTENT.value())
                .message(messageSource.getMessage("article.delete.success", null, null))
                .build();
        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }
}
