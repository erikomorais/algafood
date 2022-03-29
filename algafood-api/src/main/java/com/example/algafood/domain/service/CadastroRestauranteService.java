package com.example.algafood.domain.service;
import com.example.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.example.algafood.domain.model.Cozinha;
import com.example.algafood.domain.model.Restaurante;
import com.example.algafood.domain.repository.CozinhaRepository;
import com.example.algafood.domain.repository.RestauranteRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CadastroRestauranteService {

    private RestauranteRepository restauranteRepository;
    private CozinhaRepository cozinhaRepository;

    public List<Restaurante> listar() {
        return restauranteRepository.listar();
    }

    public Restaurante buscar(Long idRestaurante) {
        Restaurante r = restauranteRepository.buscar(idRestaurante);
        if(r == null) {
            throw new EntidadeNaoEncontradaException(String.format("Restaurante com cósigo %d", idRestaurante));
        }
        return r;
    }

    public Restaurante adicionar(Restaurante restaurante){
        Long cozinhaId = restaurante.getCozinha().getId();
        Cozinha cozinha = cozinhaRepository.buscar(cozinhaId);
        if (cozinha == null) {
          throw new EntidadeNaoEncontradaException(String.format("Não existe cadastro de cozinha com código %d", cozinhaId));
        }
        restaurante.setCozinha(cozinha);
        return restauranteRepository.salvar(restaurante);
    }

    public Restaurante atualizar(Long idRestaurante, Restaurante restaurante){
        Restaurante restauranteAtualizar = buscar(idRestaurante);
        if (restaurante.getCozinha() == null){
            throw new IllegalStateException(String.format("Cozinha não informada"));
        }
        Long cozinhaId = restaurante.getCozinha().getId();
        Cozinha cozinha = cozinhaRepository.buscar(cozinhaId);
        if (cozinha == null) {
            throw new EntidadeNaoEncontradaException(String.format("Não existe cadastro de cozinha com código %d", cozinhaId));
        }
        BeanUtils.copyProperties(restaurante,restauranteAtualizar,"id");
        restauranteAtualizar.setCozinha(cozinha);
        return restauranteRepository.salvar(restauranteAtualizar);
    }

    public void excluir(Long idRestaurante) {
        try {
            restauranteRepository.remover(idRestaurante);
        }catch (EmptyResultDataAccessException e){
            throw new EntidadeNaoEncontradaException(String.format("Restaurante com código %s",idRestaurante));
        }
    }
}
