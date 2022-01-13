package com.example.algafood.di.notificacao;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("notificador.email")
public class NotificadorProperties {

    /**
     * Host do servidor de e-mail
     */
    private String hostServidor;
    /**
     * Porta do servidor de e-mail
     */
    private Integer portaServidor;

    public String getHostServidor() {
        return hostServidor;
    }

    public Integer getPortaServidor() {
        return portaServidor;
    }

    public void setHostServidor(String hostServidor) {
        this.hostServidor = hostServidor;
    }

    public void setPortaServidor(Integer portaServidor) {
        this.portaServidor = portaServidor;
    }
}
