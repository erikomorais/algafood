package com.example.algafood.di.service;

import com.example.algafood.di.modelo.Cliente;
import com.example.algafood.di.notificacao.NotificadorEmail;
import org.springframework.stereotype.Component;

@Component
public class AtivacaoClienteService {

    private NotificadorEmail notificador;

    public AtivacaoClienteService(NotificadorEmail notificador) {
        this.notificador = notificador;
        System.out.println("AtivacaoClienteService.AtivacaoClienteService: " + notificador );
    }
    public void ativar(Cliente cliente){
        cliente.ativar();
        notificador.notificar(cliente, "Seu cadastro no sistema está ativo!");
    }
}
