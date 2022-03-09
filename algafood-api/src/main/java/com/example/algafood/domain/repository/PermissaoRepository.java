package com.example.algafood.domain.repository;

import com.example.algafood.domain.model.Permissao;

import java.util.List;

public interface PermissaoRepository {
    List<Permissao> listar();
    Permissao buscar(Long id);
    Permissao salvar(Permissao Permissao);
    void remover(Permissao Permissao);

}
