package com.inventory.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ApiResponse<T> {

    private boolean success;
    private String message;
    private int status;
    private LocalDateTime timestamp;
    private T data;

    public static <T> ApiResponse<T> success(T data,
                                             String message,
                                             int status){

        return ApiResponse.<T>builder()
                .success(true)
                .message(message)
                .status(status)
                .timestamp(LocalDateTime.now())
                .data(data)
                .build();
    }

    public static <T> ApiResponse<T> failure(String message,
                                             int status){

        return ApiResponse.<T>builder()
                .success(false)
                .message(message)
                .status(status)
                .timestamp(LocalDateTime.now())
                .data(null)
                .build();
    }

}