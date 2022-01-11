package com.example.algafood.di.service;

import com.example.algafood.di.notificacao.NivelUrgencia;
import com.example.algafood.di.modelo.Cliente;
import com.example.algafood.di.notificacao.Notificador;
import com.example.algafood.di.notificacao.TipoDoNotificador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;


@Component
public class AtivacaoClienteService {

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    public void ativar(Cliente cliente){
        cliente.ativar();
        eventPublisher.publishEvent(new ClienteAtivadoEvent(cliente));

    }
}
