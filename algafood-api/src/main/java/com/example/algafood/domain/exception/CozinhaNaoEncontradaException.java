package com.example.algafood.domain.exception;

public class CozinhaNaoEncontradaException extends EntidadeNaoEncontradaException{
    public static final String COZINHA_NAO_ENCONTRADA = "Cozinha de id %s não encontrada";
    public CozinhaNaoEncontradaException(String message) {
        super(message);
    }

    public CozinhaNaoEncontradaException(Long estadoId) {
        this (String.format(COZINHA_NAO_ENCONTRADA, estadoId) );
    }
}
