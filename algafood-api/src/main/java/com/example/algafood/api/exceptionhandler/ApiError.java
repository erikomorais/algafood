package com.example.algafood.api.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import static com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Builder;
import lombok.Getter;

@JsonInclude(Include.NON_NULL)
@Getter
@Builder
public class ApiError {
    private Integer status;
    private String type;
    private String title;
    private String detail;
}
