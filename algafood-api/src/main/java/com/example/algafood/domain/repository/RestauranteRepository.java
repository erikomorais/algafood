package com.example.algafood.domain.repository;

import com.example.algafood.domain.model.Cozinha;
import com.example.algafood.domain.model.Restaurante;

import java.util.List;

public interface RestauranteRepository {
    List<Restaurante> listar();
    Restaurante buscar(Long id);
    Restaurante salvar(Restaurante restaurante);
    void remover(Restaurante restaurante);

}
