package com.example.algafood.di.notificacao;

import com.example.algafood.di.modelo.Cliente;
import org.springframework.stereotype.Component;

@Component
public class NotificadorEmail implements Notificador {

    public NotificadorEmail() {
        System.out.println("NotificadorEmail.NotificadorEmail");
    }
    @Override
    public void notificar(Cliente cliente, String mensagem) {
        System.out.printf("Notificando %s através do email %s: %s\n",
                cliente.getNome(), cliente.getEmail(), mensagem);

    }
}
