package com.example.algafood;

import com.example.algafood.di.notificacao.NotificadorEmail;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AlgaConfig {

    @Bean
    public NotificadorEmail notificadorEmail(){
        NotificadorEmail notificadorEmail = new NotificadorEmail("smtp.algamail.com.br") ;
        notificadorEmail.setCaixaAlta(true);
        return notificadorEmail;
    }

}
