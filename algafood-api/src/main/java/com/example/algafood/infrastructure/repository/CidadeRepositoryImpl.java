package com.example.algafood.infrastructure.repository;

import com.example.algafood.domain.model.Cidade;
import com.example.algafood.domain.repository.CidadeRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class CidadeRepositoryImpl implements CidadeRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Cidade> listar() {
        return entityManager.createQuery("select  c from Cidade c", Cidade.class).getResultList();
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
    public void remover(Long cidadeId) {
        Cidade cidade = buscar(cidadeId);
        if (cidade ==null){
            throw new EmptyResultDataAccessException(1);
        }
        entityManager.remove(cidade);
    }
}
