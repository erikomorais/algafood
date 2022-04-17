package com.example.algafood.infrastructure.repository;

import com.example.algafood.domain.model.Restaurante;
import com.example.algafood.domain.repository.RestauranteRepositoryQueries;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.util.List;

@Repository

public class RestauranteRepositoryImpl implements RestauranteRepositoryQueries {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Restaurante> find(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal) {

        CriteriaBuilder criteriaBuilder =  manager.getCriteriaBuilder();
        CriteriaQuery<Restaurante> criteria = criteriaBuilder.createQuery(Restaurante.class);
        Root<Restaurante> root = criteria.from(Restaurante.class);
        Predicate nomePredicate = criteriaBuilder.like(root.get("nome"), nome);
        Predicate taxaFreteMaiorQue = criteriaBuilder.greaterThanOrEqualTo(root.get("taxaFrete"), taxaFreteInicial);
        Predicate taxaFreteMenorQue = criteriaBuilder.lessThanOrEqualTo(root.get("taxaFrete"), taxaFreteFinal);
        criteria.where(criteriaBuilder.or(nomePredicate,taxaFreteMaiorQue,taxaFreteMenorQue));
        TypedQuery<Restaurante> query=  manager.createQuery(criteria);
        return query.getResultList();
        
    }
}
