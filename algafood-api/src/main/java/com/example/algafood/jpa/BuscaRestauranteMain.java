package com.example.algafood.jpa;

import com.example.algafood.AlgafoodApiApplication;
import com.example.algafood.domain.model.Restaurante;
import com.example.algafood.domain.repository.RestauranteRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import java.math.BigDecimal;

public class BuscaRestauranteMain {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        RestauranteRepository restauranteRepository = applicationContext.getBean(RestauranteRepository.class);
        Restaurante restaurante = restauranteRepository.buscar(2l);
        System.out.printf("Id %d - %s (taxa: R$ %.2f)%n", restaurante.getId(),restaurante.getNome(), restaurante.getTaxaFrete());


    }
}
