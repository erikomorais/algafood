package com.example.algafood.domain.service;

import com.example.algafood.core.validation.ValidacaoException;
import com.example.algafood.domain.exception.CozinhaNaoEncontradaException;
import com.example.algafood.domain.exception.NegocioException;
import com.example.algafood.domain.exception.RestauranteNaoEncontradoException;
import com.example.algafood.domain.model.Cozinha;
import com.example.algafood.domain.model.Restaurante;
import com.example.algafood.domain.repository.RestauranteRepository;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.SmartValidator;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class CadastroRestauranteService {


    public static final String COZINHA_NAO_INFORMADA = "Cozinha não informada";
    private RestauranteRepository restauranteRepository;
    private CadastroCozinhaService cozinhaRepository;

    @Autowired
    private SmartValidator validator;

    public List<Restaurante> listar() {
        return restauranteRepository.findAll();
    }

    public Restaurante buscar(Long idRestaurante) {
        return restauranteRepository.findById(idRestaurante).orElseThrow(
                ()->new RestauranteNaoEncontradoException(idRestaurante)
        );
    }

    public Restaurante adicionar(Restaurante restaurante){

            Cozinha cozinha = cozinhaRepository.buscar(restaurante.getCozinha().getId());
            restaurante.setCozinha(cozinha);
            return restauranteRepository.save(restaurante);
    }

    public Restaurante atualizar(Long idRestaurante, Restaurante restaurante){
        Restaurante restauranteAtualizar = buscar(idRestaurante);
        if (restaurante.getCozinha() == null){
            throw new NullPointerException(COZINHA_NAO_INFORMADA);
        }
        BeanUtils.copyProperties(restaurante,restauranteAtualizar,"id", "dataCadastro");
        Long cozinhaId = restaurante.getCozinha().getId();
        try {
            Cozinha cozinha = cozinhaRepository.buscar(cozinhaId);
            restauranteAtualizar.setCozinha(cozinha);
            return restauranteRepository.save(restauranteAtualizar);
        }catch (CozinhaNaoEncontradaException e){
            throw new NegocioException(e.getMessage());
        }
    }

    public void excluir(Long idRestaurante) {
        try {
            restauranteRepository.deleteById(idRestaurante);
        }catch (EmptyResultDataAccessException e){
            throw new RestauranteNaoEncontradoException(idRestaurante);
        }
    }

    public Restaurante atualizarParcial(Long idRestaurante, Map<String, Object> campos) {
        Restaurante restauranteAtualizar = buscar(idRestaurante);
        merge(campos,restauranteAtualizar);
        validate(restauranteAtualizar, "restaurante");
        return atualizar(idRestaurante,restauranteAtualizar);
    }

    private void validate(Restaurante restaurante, String objectName) {
        BeanPropertyBindingResult bindingResult =  new BeanPropertyBindingResult(restaurante,objectName);
        validator.validate(restaurante,bindingResult);
        if (bindingResult.hasErrors()){
            throw new ValidacaoException(bindingResult);
        }
    }

    private void merge(Map<String, Object> dadosOrigem, Restaurante restauranteAtualizar) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES,true);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
        Restaurante restauranteOrigem = objectMapper.convertValue(dadosOrigem, Restaurante.class);

        dadosOrigem.forEach((nomePropriedade,valorPropriedade)->{
            Field field = ReflectionUtils.findField(Restaurante.class, nomePropriedade);
            field.setAccessible(true);
            Object novoValor = ReflectionUtils.getField(field,restauranteOrigem);
            ReflectionUtils.setField(field,restauranteAtualizar,novoValor);

        });
    }
}
