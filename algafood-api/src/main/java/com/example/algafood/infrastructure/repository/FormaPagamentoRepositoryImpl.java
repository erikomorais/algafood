package com.example.algafood.infrastructure.repository;

import com.example.algafood.domain.model.FormaPagamento;
import com.example.algafood.domain.repository.FormaPagamentoRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class FormaPagamentoRepositoryImpl implements FormaPagamentoRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<FormaPagamento> listar() {
        return entityManager.createQuery("from FormaPagamento", FormaPagamento.class).getResultList();
    }

    @Override
    public FormaPagamento buscar(Long id) {
        return entityManager.find(FormaPagamento.class, id);
    }

    @Transactional
    @Override
    public FormaPagamento salvar(FormaPagamento FormaPagamento) {
        return entityManager.merge(FormaPagamento);
    }

    @Transactional
    @Override
    public void remover(FormaPagamento FormaPagamento) {
        FormaPagamento = buscar(FormaPagamento.getId());
        entityManager.remove(FormaPagamento);
    }
}
