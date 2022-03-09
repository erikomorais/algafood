package com.example.algafood.jpa;

import com.example.algafood.AlgafoodApiApplication;
import com.example.algafood.domain.model.Restaurante;
import com.example.algafood.domain.repository.RestauranteRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import java.math.BigDecimal;

public class AlteracaoRestauranteMain {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        RestauranteRepository restauranteRepository = applicationContext.getBean(RestauranteRepository.class);
        Restaurante restaurante = new Restaurante();
        restaurante.setId(2l);
        restaurante.setNome("Cozinha do Bob Flay");
        restaurante.setTaxaFrete(BigDecimal.ZERO); //frete grátis :)
        restauranteRepository.salvar(restaurante);


    }
}
