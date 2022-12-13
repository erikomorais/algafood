package com.example.algafood;

import com.example.algafood.domain.exception.CozinhaNaoEncontradaException;
import com.example.algafood.domain.exception.EntidadeEmUsoException;
import com.example.algafood.domain.model.Cozinha;
import com.example.algafood.domain.service.CadastroCozinhaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolationException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class CadastroCozinhaIntegrationTests {

    @Autowired
    private CadastroCozinhaService cadastroCozinhaService;

    @Test
    void cadastroCozinhaComSucesso(){
        Cozinha cozinha = Cozinha.builder().nome("chinesa").build();
        cozinha = cadastroCozinhaService.adicionar(cozinha);
        assertThat(cozinha).isNotNull();
        assertThat(cozinha.getId()).isNotNull();


    }
    @Test()
    void deveFalhar_AoCadastrarCozinhaSemNome(){
        Cozinha cozinha = Cozinha.builder().nome(null).build();
        ConstraintViolationException erroEsperado = assertThrows(ConstraintViolationException.class, () -> cadastroCozinhaService.adicionar(cozinha));
        assertThat(erroEsperado).isNotNull();
    }

    @Test()
    void deveFalhar_AoExcluirCozinhaEmUso(){
        EntidadeEmUsoException erroEsperado =
                assertThrows(EntidadeEmUsoException.class, () -> {
                    cadastroCozinhaService.excluir(1L);
                });

        assertThat(erroEsperado).isNotNull();
    }

    @Test()
    void deveFalhar_AoExcluirCozinhaEmInexistente(){
        CozinhaNaoEncontradaException erroEsperado =
                assertThrows(CozinhaNaoEncontradaException.class, () -> {
                    cadastroCozinhaService.excluir(100L);
                });

        assertThat(erroEsperado).isNotNull();
    }
}
