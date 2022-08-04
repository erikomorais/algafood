package com.example.algafood.domain.service;

import com.example.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.example.algafood.domain.model.Cozinha;
import com.example.algafood.domain.model.Restaurante;
import com.example.algafood.domain.repository.RestauranteRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class CadastroRestauranteService {

    public static final String RESTAURANTE_NAO_ENCONTRADO = "Restaurante com cósigo %d não encontrado";
    public static final String COZINHA_NAO_INFORMADA = "Cozinha não informada";
    private RestauranteRepository restauranteRepository;
    private CadastroCozinhaService cozinhaRepository;

    public List<Restaurante> listar() {
        return restauranteRepository.findAll();
    }

    public Restaurante buscar(Long idRestaurante) {
        return restauranteRepository.findById(idRestaurante).orElseThrow(
                ()->new EntidadeNaoEncontradaException(String.format(RESTAURANTE_NAO_ENCONTRADO, idRestaurante))
        );
    }

    public Restaurante adicionar(Restaurante restaurante){
        Long cozinhaId = restaurante.getCozinha().getId();
        Cozinha cozinha = cozinhaRepository.buscar(cozinhaId);
        restaurante.setCozinha(cozinha);
        return restauranteRepository.save(restaurante);
    }

    public Restaurante atualizar(Long idRestaurante, Restaurante restaurante){
        Restaurante restauranteAtualizar = buscar(idRestaurante);
        if (restaurante.getCozinha() == null){
            throw new NullPointerException(COZINHA_NAO_INFORMADA);
        }
        Long cozinhaId = restaurante.getCozinha().getId();
        Cozinha cozinha = cozinhaRepository.buscar(cozinhaId);
        BeanUtils.copyProperties(restaurante,restauranteAtualizar,"id");
        restauranteAtualizar.setCozinha(cozinha);
        return restauranteRepository.save(restauranteAtualizar);
    }

    public void excluir(Long idRestaurante) {
        try {
            restauranteRepository.deleteById(idRestaurante);
        }catch (EmptyResultDataAccessException e){
            throw new EntidadeNaoEncontradaException(String.format(RESTAURANTE_NAO_ENCONTRADO,idRestaurante));
        }
    }

    public Restaurante atualizarParcial(Long idRestaurante, Map<String, Object> campos) {
        Restaurante restauranteAtualizar = buscar(idRestaurante);
        merge(campos,restauranteAtualizar);
        return atualizar(idRestaurante,restauranteAtualizar);
    }

    private void merge(Map<String, Object> dadosOrigem, Restaurante restauranteAtualizar) {
        ObjectMapper objectMapper = new ObjectMapper();
        Restaurante restauranteOrigem = objectMapper.convertValue(dadosOrigem, Restaurante.class);

        dadosOrigem.forEach((nomePropriedade,valorPropriedade)->{
            Field field = ReflectionUtils.findField(Restaurante.class, nomePropriedade);
            field.setAccessible(true);
            Object novoValor = ReflectionUtils.getField(field,restauranteOrigem);
            ReflectionUtils.setField(field,restauranteAtualizar,novoValor);

        });
    }
}
