package com.example.algafood.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class EstadoNaoInformadoException extends RuntimeException {
    public EstadoNaoInformadoException(String message) {
        super(message);
    }
   }
