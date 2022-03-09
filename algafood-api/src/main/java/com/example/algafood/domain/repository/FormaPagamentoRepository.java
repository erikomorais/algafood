    package com.example.algafood.domain.repository;

import com.example.algafood.domain.model.FormaPagamento;

import java.util.List;

public interface FormaPagamentoRepository {
    List<FormaPagamento> listar();
    FormaPagamento buscar(Long id);
    FormaPagamento salvar(FormaPagamento FormaPagamento);
    void remover(FormaPagamento FormaPagamento);

}
