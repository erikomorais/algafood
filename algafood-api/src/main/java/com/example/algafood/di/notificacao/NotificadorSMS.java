package com.example.algafood.di.notificacao;
import com.example.algafood.di.modelo.Cliente;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
@TipoDoNotificador(NivelUrgencia.URGENT)
public class NotificadorSMS implements Notificador {

    private boolean caixaAlta;

    public NotificadorSMS() {
        System.out.println("NotificadorSMS.NotificadorSMS");
    }


    @Override
    public void notificar(Cliente cliente, String mensagem) {
        if (this.caixaAlta){
            mensagem = mensagem.toUpperCase(Locale.ROOT);
        }
        System.out.printf("Notificando %s através do SMS ao número %S: %s\n",
                cliente.getNome(), cliente.getTelefone(),  mensagem);

    }
    public void setCaixaAlta(boolean caixaAlta) {
        this.caixaAlta = caixaAlta;
    }



}
