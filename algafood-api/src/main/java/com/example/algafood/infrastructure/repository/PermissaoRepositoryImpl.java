package com.example.algafood.infrastructure.repository;

import com.example.algafood.domain.model.Permissao;
import com.example.algafood.domain.repository.PermissaoRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class PermissaoRepositoryImpl implements PermissaoRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Permissao> listar() {
        return entityManager.createQuery("from Permissao", Permissao.class).getResultList();
    }

    @Override
    public Permissao buscar(Long id) {
        return entityManager.find(Permissao.class, id);
    }

    @Transactional
    @Override
    public Permissao salvar(Permissao Permissao) {
        return entityManager.merge(Permissao);
    }

    @Transactional
    @Override
    public void remover(Permissao Permissao) {
        Permissao = buscar(Permissao.getId());
        entityManager.remove(Permissao);
    }
}
