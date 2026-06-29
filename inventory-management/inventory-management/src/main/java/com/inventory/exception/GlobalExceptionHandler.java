package com.inventory.exception;

import com.inventory.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<Object>> handleNotFound(
            ResourceNotFoundException ex){

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ApiResponse.failure(
                        ex.getMessage(),
                        HttpStatus.NOT_FOUND.value()));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResponse<Object>> handleIllegalArgument(
            IllegalArgumentException ex){

        return ResponseEntity.badRequest()
                .body(ApiResponse.failure(
                        ex.getMessage(),
                        HttpStatus.BAD_REQUEST.value()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Object>> validationException(
            MethodArgumentNotValidException ex){

        String error = ex.getBindingResult()
                .getFieldError()
                .getDefaultMessage();

        return ResponseEntity.badRequest()
                .body(ApiResponse.failure(
                        error,
                        HttpStatus.BAD_REQUEST.value()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Object>> handleException(
            Exception ex){

        return ResponseEntity.internalServerError()
                .body(ApiResponse.failure(
                        ex.getMessage(),
                        HttpStatus.INTERNAL_SERVER_ERROR.value()));
    }

}