package com.example.algafood.domain.repository;

import com.example.algafood.domain.model.Cozinha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CozinhaRepository extends JpaRepository<Cozinha,Long> {

    List<Cozinha> findTodasByNomeContaining(String nome);

    Optional<Cozinha> findByNome(String nome);

    boolean existsByNome(String nome);


}
