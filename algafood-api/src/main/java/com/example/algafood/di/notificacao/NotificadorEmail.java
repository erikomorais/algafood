package com.example.algafood.di.notificacao;
import com.example.algafood.di.modelo.Cliente;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
@TipoDoNotificador(NivelUrgencia.URGENT)
public class NotificadorEmail implements Notificador {

    public NotificadorEmail() {
        System.out.println("Notificador email real");
    }

    @Override
    public void notificar(Cliente cliente, String mensagem) {
        System.out.printf("Notificando %s através do email %s: %s\n",
                cliente.getNome(), cliente.getEmail(),  mensagem);

    }




}
