package com.example.algafood.di.service;

import com.example.algafood.di.modelo.Cliente;
import com.example.algafood.di.notificacao.Notificador;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class AtivacaoClienteService {


    private List<Notificador> notificadores;

    public AtivacaoClienteService(List<Notificador> notificadores) {
        this.notificadores = notificadores;
        System.out.println("AtivacaoClienteService.AtivacaoClienteService: " + notificadores );
    }
    public void ativar(Cliente cliente){
        cliente.ativar();
        for (Notificador notificador : notificadores) {
            notificador.notificar(cliente, "Seu cadastro no sistema está ativo!");
        }
    }
}
