package com.example.algafood.di.service;

import com.example.algafood.di.modelo.Cliente;
import com.example.algafood.di.notificacao.Notificador;
import org.springframework.stereotype.Component;


public class AtivacaoClienteService {


    private Notificador notificador;

    public AtivacaoClienteService(Notificador notificador) {
        this.notificador = notificador;
        System.out.println("AtivacaoClienteService.AtivacaoClienteService: " + notificador );
    }
    public void ativar(Cliente cliente){
        cliente.ativar();
        notificador.notificar(cliente, "Seu cadastro no sistema está ativo!");
    }
}
