package com.vtoan1517.advice;

import com.vtoan1517.dto.ErrorResponse;
import com.vtoan1517.exception.ArticleNotFoundException;
import com.vtoan1517.exception.ResourceNotFoundException;
import com.vtoan1517.exception.MethodNotAllowException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ApplicationExceptionHandler {
    @ExceptionHandler(ArticleNotFoundException.class)
    public ResponseEntity<Object> handleBusinessException(ArticleNotFoundException ex) {
        ErrorResponse errorDetails = ErrorResponse.builder()
                .timestamp(new Date())
                .status(HttpStatus.NOT_FOUND.value())
                .error("Lỗi không tìm thấy")
                .message(ex.getMessage())
                .build();
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
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

}
