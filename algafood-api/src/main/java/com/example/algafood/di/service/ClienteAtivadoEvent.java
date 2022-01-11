package com.example.algafood.di.service;

import com.example.algafood.di.modelo.Cliente;

public class ClienteAtivadoEvent {

    private Cliente cliente;


    public ClienteAtivadoEvent(Cliente cliente) {
        super();
        this.cliente = cliente;
    }

    public Cliente getCliente() {
        return this.cliente;
    }
}
