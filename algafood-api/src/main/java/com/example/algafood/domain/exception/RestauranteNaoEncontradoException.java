package com.example.algafood.domain.exception;

public class RestauranteNaoEncontradoException extends EntidadeNaoEncontradaException{
    public static final String RESTAURANTE_NAO_ENCONTRADO = "Restaurante com cósigo %d não encontrado";

    public RestauranteNaoEncontradoException(String message) {
        super(message);
    }

    public RestauranteNaoEncontradoException(Long estadoId) {
        this (String.format(RESTAURANTE_NAO_ENCONTRADO, estadoId) );
    }
}
