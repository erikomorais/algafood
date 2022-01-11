package com.example.algafood.di.notificacao;
import com.example.algafood.di.modelo.Cliente;
import org.springframework.stereotype.Component;

@Component
@TipoDoNotificador(NivelUrgencia.SEM_URGENCIA)
public class NotificadorEmailMock implements Notificador {

    public NotificadorEmailMock() {
        System.out.println("Notificador email MOCK");
    }

    @Override
    public void notificar(Cliente cliente, String mensagem) {
            System.out.printf("MOCK: Notificação seria eviada para %s através do email %s: %s\n",
                cliente.getNome(), cliente.getEmail(),mensagem);

    }



}
