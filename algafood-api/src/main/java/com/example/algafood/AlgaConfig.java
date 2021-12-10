package com.example.algafood;

import com.example.algafood.di.notificacao.NotificadorEmail;
import com.example.algafood.di.service.AtivacaoClienteService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AlgaConfig {

    @Bean
    public NotificadorEmail notificadorEmail(){
        NotificadorEmail notificadorEmail = new NotificadorEmail("smtp.algamail.com.br") ;
        notificadorEmail.setCaixaAlta(false);
        return notificadorEmail;
    }

    @Bean
    public AtivacaoClienteService ativacaoClienteService(){
        return new AtivacaoClienteService(this.notificadorEmail());
    }

}
