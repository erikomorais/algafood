package com.example.algafood.domain.repository;

import com.example.algafood.domain.model.Estado;

import java.util.List;

public interface EstadoRepository {
    List<Estado> listar();
    Estado buscar(Long id);
    Estado salvar(Estado estado);
    void remover(Long estadoId);

}
