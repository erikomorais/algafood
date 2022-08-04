package com.example.algafood.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

//@ResponseStatus(value = HttpStatus.NOT_FOUND)//, reason = "Entidade não encontrada")
public class EntidadeNaoEncontradaException extends ResponseStatusException {
    public EntidadeNaoEncontradaException(HttpStatus status, String reason) {
        super(status, reason);
    }

    public EntidadeNaoEncontradaException(String message) {
        this(HttpStatus.NOT_FOUND, message);
    }
}
