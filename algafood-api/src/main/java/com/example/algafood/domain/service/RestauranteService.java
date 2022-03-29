package com.example.algafood.domain.service;
import com.example.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.example.algafood.domain.model.Restaurante;
import com.example.algafood.domain.repository.RestauranteRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RestauranteService {

    private RestauranteRepository restauranteRepository;
    public List<Restaurante> listar() {
        return restauranteRepository.listar();
    }

    public Restaurante buscar(Long idRestaurante) {
        Restaurante r = restauranteRepository.buscar(idRestaurante);
        if(r == null) {
            throw new EntidadeNaoEncontradaException(String.format("Restaurante com cósigo %s", idRestaurante));
        }
        return r;
    }
}
