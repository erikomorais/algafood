package com.example.algafood.di.notificacao;
import com.example.algafood.NivelUrgencia;
import com.example.algafood.di.modelo.Cliente;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
@TipoDoNotificador(NivelUrgencia.NORMAL)
public class NotificadorEmail implements Notificador {

    private boolean caixaAlta;
    private String hostServidorSmtp;

    public NotificadorEmail() {
        this.hostServidorSmtp = "host ServidorSmtp";
        System.out.println("NotificadorEmail.NotificadorEmail");
    }


    @Override
    public void notificar(Cliente cliente, String mensagem) {
        if (this.caixaAlta){
            mensagem = mensagem.toUpperCase(Locale.ROOT);
        }
        System.out.printf("Notificando %s através do email %s usando SMTP %s: %s\n",
                cliente.getNome(), cliente.getEmail(), this.hostServidorSmtp, mensagem);

    }
    public void setCaixaAlta(boolean caixaAlta) {
        this.caixaAlta = caixaAlta;
    }



}
