package com.example.algafood.domain.exception;

public class CidadeNaoEncontradaException extends EntidadeNaoEncontradaException{
    public static final String CIDADE_NAO_ENCONTRADA = "Cidade com coódigo %d não encontrada";
    public CidadeNaoEncontradaException(String message) {
        super(message);
    }

    public CidadeNaoEncontradaException(Long estadoId) {
        this (String.format(CIDADE_NAO_ENCONTRADA, estadoId) );
    }
}
