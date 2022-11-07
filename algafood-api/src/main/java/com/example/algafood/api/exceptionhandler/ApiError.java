package com.example.algafood.api.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

import static com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
@Getter
@Builder
public class ApiError {
    private Integer status;
    private LocalDateTime timestamp;
    private String type;
    private String title;
    private String detail;
    private String userMessage;
}
