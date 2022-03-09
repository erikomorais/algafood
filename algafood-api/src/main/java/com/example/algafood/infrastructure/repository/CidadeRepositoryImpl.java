package com.example.algafood.infrastructure.repository;

import com.example.algafood.domain.model.Cidade;
import com.example.algafood.domain.repository.CidadeRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class CidadeRepositoryImpl implements CidadeRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Cidade> listar() {
        return entityManager.createQuery("from Cidade", Cidade.class).getResultList();
    }

    @Override
    public Cidade buscar(Long id) {
        return entityManager.find(Cidade.class, id);
    }

    @Transactional
    @Override
    public Cidade salvar(Cidade Cidade) {
        return entityManager.merge(Cidade);
    }

    @Transactional
    @Override
    public void remover(Cidade Cidade) {
        Cidade = buscar(Cidade.getId());
        entityManager.remove(Cidade);
    }
}
