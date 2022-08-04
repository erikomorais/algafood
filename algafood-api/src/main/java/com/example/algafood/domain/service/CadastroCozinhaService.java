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

    public static final String COZINHA_NAO_ENCONTRADA = "Cozinha de id %s não encontrada";
    public static final String COZINHA_EM_USO = "Cozinha com código %s em uso";
    private CozinhaRepository cozinhaRepository;


    public List<Cozinha> listar() {
        return cozinhaRepository.findAll();
    }

    public Cozinha buscar(Long cozinhaId) {
        return cozinhaRepository.findById(cozinhaId)
                .orElseThrow(()-> new EntidadeNaoEncontradaException(String.format(COZINHA_NAO_ENCONTRADA,
                        cozinhaId)));
    }

    public void excluir(Long cozinhaId ) {
        try{
            cozinhaRepository.deleteById(cozinhaId);
        }catch (DataIntegrityViolationException e){
            throw new EntidadeEmUsoException(String.format(COZINHA_EM_USO,cozinhaId));
        }catch (EmptyResultDataAccessException e){
            throw new EntidadeNaoEncontradaException(String.format(COZINHA_NAO_ENCONTRADA,cozinhaId));
        }
    }

    public Cozinha adicionar(Cozinha cozinha) {
        cozinha = cozinhaRepository.save(cozinha);
        return cozinha;
    }

    public Cozinha atualizar(@PathVariable Long cozinhaId ,@RequestBody Cozinha cozinha) {
        Cozinha cozinhaAtual = buscar(cozinhaId);
        BeanUtils.copyProperties(cozinha, cozinhaAtual, "id");
        return cozinhaRepository.save(cozinhaAtual);
    }




    public Cozinha buscarPorNome(String nome) {
        return cozinhaRepository.findByNome(nome).orElseThrow(
                ()-> new EntidadeNaoEncontradaException(String.format("Não há cozinha cadastrada com nome %s",nome))
        );
    }
}
