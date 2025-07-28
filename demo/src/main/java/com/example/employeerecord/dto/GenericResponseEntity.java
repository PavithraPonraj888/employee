package com.example.employeerecord.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@Builder
public class GenericResponseEntity<T> {
    private String message;
    private T data;
    private boolean success;
    private HttpStatus status;
    private int statusCode;
}
