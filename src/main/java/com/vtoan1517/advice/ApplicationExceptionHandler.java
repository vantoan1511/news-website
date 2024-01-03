package com.vtoan1517.advice;

import com.vtoan1517.dto.ErrorResponse;
import com.vtoan1517.exception.ArticleNotFoundException;
import com.vtoan1517.exception.CategoryNotFoundException;
import com.vtoan1517.exception.MethodNotAllowException;
import com.vtoan1517.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ApplicationExceptionHandler {
    @ExceptionHandler(ArticleNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Object handleArticleNotFoundException(ArticleNotFoundException ex,
                                                 HttpServletRequest request) {
        if (isAPIRequest(request)) {
            return new ResponseEntity<>(errorDetails(HttpStatus.NOT_FOUND.value(), ex.getMessage(), ex.getMessage()),
                    HttpStatus.NOT_FOUND);
        }

        String viewName = isAdminRequest(request) ? "admin/404" : "web/404";
        return new ModelAndView(viewName);
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Object handleCategoryNotFoundException(CategoryNotFoundException ex,
                                                  HttpServletRequest request) {
        if (isAPIRequest(request)) {
            return new ResponseEntity<>(errorDetails(HttpStatus.NOT_FOUND.value(), ex.getMessage(), ex.getMessage()),
                    HttpStatus.NOT_FOUND);
        }

        String viewName = isAdminRequest(request) ? "admin/404" : "web/404";
        return new ModelAndView(viewName);
    }

    @ExceptionHandler(MethodNotAllowException.class)
    public ResponseEntity<Object> handleMethodNotAllowException(MethodNotAllowException ex) {
        ErrorResponse errorDetails = ErrorResponse.builder()
                .timestamp(new Date())
                .status(HttpStatus.METHOD_NOT_ALLOWED.value())
                .error("Lỗi thao tác không hợp lệ hoặc không được phép")
                .message(ex.getMessage())
                .build();
        return new ResponseEntity<>(errorDetails, HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleFileNotFoundException(ResourceNotFoundException ex) {
        ErrorResponse errorDetails = ErrorResponse.builder()
                .timestamp(new Date())
                .status(HttpStatus.NOT_FOUND.value())
                .error("Không tìm thấy tài nguyên")
                .message(ex.getMessage())
                .build();
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });
        ErrorResponse errorDetails = ErrorResponse.builder()
                .timestamp(new Date())
                .status(HttpStatus.BAD_REQUEST.value())
                .error("Lỗi dữ liệu không hợp lệ")
                .message(errors)
                .build();
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    private boolean isAPIRequest(HttpServletRequest request) {
        return request.getRequestURI().contains("api");
    }

    private boolean isAdminRequest(HttpServletRequest request) {
        return request.getRequestURI().contains("admin");
    }

    private ErrorResponse errorDetails(int status, String error, String message) {
        return ErrorResponse.builder()
                .timestamp(new Date())
                .status(status)
                .error(error)
                .message(message)
                .build();
    }
}
