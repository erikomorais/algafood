package com.example.algafood.domain.exception;

public class EstadoNaoEncontradoException extends EntidadeNaoEncontradaException{
    public static final String ESTADO_NAO_ENCONTRADO = "Não existe cadastro de Estado com código %s";

    public EstadoNaoEncontradoException(String message) {
        super(message);
    }

    public EstadoNaoEncontradoException(Long estadoId) {
        this (String.format(ESTADO_NAO_ENCONTRADO, estadoId) );
    }
}
