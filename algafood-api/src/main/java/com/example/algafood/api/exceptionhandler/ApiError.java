package com.example.algafood.api.exceptionhandler;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Builder
public class ApiError {
    private LocalDateTime dataHora;
    private String mensagem;
}
