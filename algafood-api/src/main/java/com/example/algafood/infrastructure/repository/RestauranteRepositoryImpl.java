package com.example.algafood.infrastructure.repository;

import com.example.algafood.domain.model.Restaurante;
import com.example.algafood.domain.repository.RestauranteRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class RestauranteRepositoryImpl implements RestauranteRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Restaurante> listar() {
        return entityManager.createQuery("select r from Restaurante r", Restaurante.class).getResultList();
    }

    @Override
    public Restaurante buscar(Long id) {

        return entityManager.find(Restaurante.class, id);
    }

    @Transactional
    @Override
    public Restaurante salvar(Restaurante restaurante) {
        return entityManager.merge(restaurante);
    }

    @Transactional
    @Override
    public void remover(Long idRestaurante) {
        Restaurante restaurante = buscar(idRestaurante);
        if(restaurante == null){
            throw new EmptyResultDataAccessException(1);
        }
        entityManager.remove(restaurante);

    }
}
