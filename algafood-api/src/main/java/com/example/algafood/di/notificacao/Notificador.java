package com.example.algafood.di.notificacao;

import com.example.algafood.di.modelo.Cliente;
import org.springframework.stereotype.Component;

public interface Notificador {
    void notificar(Cliente cliente, String mensagem);
}
