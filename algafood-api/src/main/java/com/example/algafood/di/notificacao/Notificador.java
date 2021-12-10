package com.example.algafood.di.notificacao;

import com.example.algafood.di.modelo.Cliente;

public interface Notificador {
    void notificar(Cliente cliente, String mensagem);
}
