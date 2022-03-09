package com.example.algafood.domain.repository;

import com.example.algafood.domain.model.Cidade;

import java.util.List;

public interface CidadeRepository {
    List<Cidade> listar();
    Cidade buscar(Long id);
    Cidade salvar(Cidade Cidade);
    void remover(Cidade Cidade);

}
