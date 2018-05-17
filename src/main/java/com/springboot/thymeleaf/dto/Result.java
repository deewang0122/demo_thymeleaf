package com.springboot.thymeleaf.dto;

import lombok.Data;

@Data
public class Result<T> {
    String message;
    Object errors;
    T data;
}
