package com.example.algafood.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public abstract class EntidadeNaoEncontradaException  extends NegocioException{

    public EntidadeNaoEncontradaException(String message) {
        super(message);
    }
}
