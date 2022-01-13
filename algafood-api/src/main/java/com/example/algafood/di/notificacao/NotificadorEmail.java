package com.example.algafood.di.notificacao;
import com.example.algafood.di.modelo.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Locale;

@TipoDoNotificador(NivelUrgencia.SEM_URGENCIA)
@Component
public class NotificadorEmail implements Notificador {

    @Autowired
    private  NotificadorProperties notificadorProperties;

    public NotificadorEmail() {
        System.out.println("Notificador email real");
    }

    @Override
    public void notificar(Cliente cliente, String mensagem) {
        System.out.println("Host: " + notificadorProperties.getHostServidor());
        System.out.println("Porta: " + notificadorProperties.getPortaServidor());
        System.out.printf("Notificando %s através do email %s: %s\n",
                cliente.getNome(), cliente.getEmail(),  mensagem);

    }




}
