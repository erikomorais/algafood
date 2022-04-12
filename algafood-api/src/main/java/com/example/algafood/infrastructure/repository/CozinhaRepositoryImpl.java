package com.example.algafood.infrastructure.repository;

import com.example.algafood.domain.model.Cozinha;
import com.example.algafood.domain.repository.CozinhaRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class CozinhaRepositoryImpl implements CozinhaRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Cozinha> listar() {
        return entityManager.createQuery("select c from Cozinha c", Cozinha.class).getResultList();
    }

    @Override
    public List<Cozinha> consultarPorNome(String nomeCozinha) {
        return entityManager.createQuery("select c from Cozinha c where c.nome like :nome")
                .setParameter("nome", "%" +  nomeCozinha + "%")
                .getResultList();
    }

    @Override
    public Cozinha buscar(Long id) {
        return entityManager.find(Cozinha.class, id);
    }

    @Transactional
    @Override
    public Cozinha salvar(Cozinha cozinha) {
        return entityManager.merge(cozinha);
    }

    @Transactional
    @Override
    public void remover(Long id) {
        Cozinha cozinha = buscar(id);
        if (cozinha == null){
            throw new EmptyResultDataAccessException(1);
        }
        entityManager.remove(cozinha);
    }
}
