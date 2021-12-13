package com.example.algafood.di.service;

import com.example.algafood.NivelUrgencia;
import com.example.algafood.di.modelo.Cliente;
import com.example.algafood.di.notificacao.Notificador;
import com.example.algafood.di.notificacao.TipoDoNotificador;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


@Component

public class AtivacaoClienteService {


    private Notificador notificador;

    public AtivacaoClienteService(@TipoDoNotificador(NivelUrgencia.NORMAL) Notificador notificador) {
        this.notificador = notificador;
        System.out.println("AtivacaoClienteService.AtivacaoClienteService: " + notificador );
    }
    public void ativar(Cliente cliente){
        cliente.ativar();
        notificador.notificar(cliente, "Seu cadastro no sistema está ativo!");
    }
}
