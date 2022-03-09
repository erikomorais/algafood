package com.example.algafood.jpa;

import com.example.algafood.AlgafoodApiApplication;
import com.example.algafood.domain.model.Restaurante;
import com.example.algafood.domain.repository.RestauranteRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import java.math.BigDecimal;

public class InclusaoRestauranteMain {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        RestauranteRepository restauranteRepository = applicationContext.getBean(RestauranteRepository.class);
        Restaurante restaurante = new Restaurante();
        restaurante.setNome("Restaurante do Simon");
        restaurante.setTaxaFrete(BigDecimal.valueOf(22.50));
        Restaurante restaurante2 = new Restaurante();
        restaurante2.setNome("Restaurante do Bob Flay");
        restaurante2.setTaxaFrete(BigDecimal.valueOf(55.44));
        restaurante = restauranteRepository.salvar(restaurante);
        restaurante2 = restauranteRepository.salvar(restaurante2);
        System.out.printf("Id %d - %s (taxa: R$ %.2f)%n", restaurante.getId(),restaurante.getNome(), restaurante.getTaxaFrete());
        System.out.printf("Id %d - %s (taxa: R$ %.2f)%n", restaurante2.getId(),restaurante2.getNome(), restaurante2.getTaxaFrete());


    }
}
