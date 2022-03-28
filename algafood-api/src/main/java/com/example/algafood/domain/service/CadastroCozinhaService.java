package com.example.algafood.domain.service;

import com.example.algafood.domain.exception.EntidadeEmUsoException;
import com.example.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.example.algafood.domain.model.Cozinha;
import com.example.algafood.domain.repository.CozinhaRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Service
@AllArgsConstructor
public class CadastroCozinhaService {

    private CozinhaRepository cozinhaRepository;


    public List<Cozinha> listar() {
        return cozinhaRepository.listar();
    }

    public Cozinha buscar(Long cozinhaId) {
        try {
            Cozinha cozinha = cozinhaRepository.buscar(cozinhaId);
            return cozinha;
        }catch(EmptyResultDataAccessException e){
            throw new EntidadeNaoEncontradaException(String.format("Cozinha de id %s não encontrada", cozinhaId));
        }
    }

    public Cozinha adicionar(Cozinha cozinha) {
        cozinha = cozinhaRepository.salvar(cozinha);
        return cozinha;
    }

    public Cozinha atualizar(@PathVariable Long cozinhaId ,@RequestBody Cozinha cozinha) {
        Cozinha cozinhaAtual = buscar(cozinhaId);
        BeanUtils.copyProperties(cozinha, cozinhaAtual, "id");
        return cozinhaRepository.salvar(cozinhaAtual);
    }

    public void excluir(Long cozinhaId ) {
        try{
            cozinhaRepository.remover(cozinhaId);
        }catch (DataIntegrityViolationException e){
            throw new EntidadeEmUsoException(String.format("Cozinha com dódigo %s",cozinhaId));
        }catch (EmptyResultDataAccessException e){
            throw new EntidadeNaoEncontradaException(String.format("Cozinha com dódigo %s não encontrada",cozinhaId));
        }



    }




}
