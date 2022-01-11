package com.example.algafood.listener;

import com.example.algafood.di.notificacao.NivelUrgencia;
import com.example.algafood.di.notificacao.Notificador;
import com.example.algafood.di.notificacao.TipoDoNotificador;
import com.example.algafood.di.service.ClienteAtivadoEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class NotificacaoService {

    @TipoDoNotificador(NivelUrgencia.SEM_URGENCIA)
    @Autowired
    private Notificador notificador;

    @EventListener
    public void clienteAtivadoListener(ClienteAtivadoEvent event){
        notificador.notificar(event.getCliente(), "Seu castro no sistema está ativo!");

    }
}
