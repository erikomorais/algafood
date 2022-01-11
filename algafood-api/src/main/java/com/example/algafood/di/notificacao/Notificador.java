package com.example.algafood.di.notificacao;

import com.example.algafood.di.modelo.Cliente;
import com.example.algafood.di.service.ClienteAtivadoEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public interface Notificador {

    void notificar(Cliente cliente, String mensagem);
}
