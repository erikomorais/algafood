package com.example.algafood.listener;

import com.example.algafood.di.service.ClienteAtivadoEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
public class EmissaoNotaFiscalService {
    @EventListener
    @Order(0)
    public void clienteAtivadoListener(ClienteAtivadoEvent event) {
        System.out.println("Emitindo nota fiscal para cliente " + event.getCliente().getNome());
    }
}
