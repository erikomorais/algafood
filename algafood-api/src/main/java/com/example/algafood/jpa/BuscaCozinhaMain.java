package com.example.algafood.jpa;

import com.example.algafood.AlgafoodApiApplication;
import com.example.algafood.domain.model.Cozinha;
import com.example.algafood.domain.repository.CozinhaRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

public class BuscaCozinhaMain {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder((AlgafoodApiApplication.class))
                .web(WebApplicationType.NONE)
                .run(args);

        CozinhaRepository cozinhaRepository = applicationContext.getBean(CozinhaRepository.class);
        Cozinha cozinha = cozinhaRepository.buscar(2L);
        System.out.printf("%d - %s\n", cozinha.getId(), cozinha.getNome());

    }
}
