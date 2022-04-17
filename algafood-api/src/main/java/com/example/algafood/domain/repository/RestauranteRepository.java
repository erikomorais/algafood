package com.example.algafood.domain.repository;

import com.example.algafood.domain.model.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface RestauranteRepository extends JpaRepository<Restaurante,Long>, RestauranteRepositoryQueries {


    List<Restaurante> queryByTaxaFreteBetween(BigDecimal taxaInicial, BigDecimal taxaFinal);

   // @Query("from Restaurante r where r.cozinha.id = :cozinhaId  and r.nome like concat('%',:nome,'%')")
    List<Restaurante> consultarPorNome(@Param("nome") String nome,@Param("cozinhaId") Long cozinhaId);

    Optional<Restaurante> findFirstRestauranteByNomeContaining(String nome);

    List<Restaurante> findTop2ByNomeContaining(String nome);

    int countByCozinhaId(Long cozinhaId);
}
