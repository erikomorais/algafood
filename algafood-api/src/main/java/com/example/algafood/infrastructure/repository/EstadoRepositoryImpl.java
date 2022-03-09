package com.example.algafood.infrastructure.repository;

import com.example.algafood.domain.model.Estado;
import com.example.algafood.domain.repository.EstadoRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class EstadoRepositoryImpl implements EstadoRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Estado> listar() {
        return entityManager.createQuery("from Estado", Estado.class).getResultList();
    }

    @Override
    public Estado buscar(Long id) {
        return entityManager.find(Estado.class, id);
    }

    @Transactional
    @Override
    public Estado salvar(Estado Estado) {
        return entityManager.merge(Estado);
    }

    @Transactional
    @Override
    public void remover(Estado Estado) {
        Estado = buscar(Estado.getId());
        entityManager.remove(Estado);
    }
}
