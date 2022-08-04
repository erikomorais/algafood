package com.example.algafood.api.controller;

import com.example.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.example.algafood.domain.model.Cozinha;
import com.example.algafood.domain.service.CadastroCozinhaService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/cozinhas")
@AllArgsConstructor
public class CozinhaController {

    private CadastroCozinhaService cadastroCozinhaService;

    @GetMapping
    public List<Cozinha> listar() {
        return cadastroCozinhaService.listar();
    }

    @GetMapping("/{cozinhaId}")
    public ResponseEntity<Cozinha> buscar(@PathVariable Long cozinhaId) {
        try{
        Cozinha cozinha = cadastroCozinhaService.buscar(cozinhaId);
         return ResponseEntity.ok(cozinha);
        }  catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cozinha adicionar(@RequestBody Cozinha cozinha) {
        cozinha = cadastroCozinhaService.adicionar(cozinha);
        return cozinha;

      }

    @PutMapping("/{cozinhaId}")
    public ResponseEntity<Cozinha> atualizar(@PathVariable Long cozinhaId ,@RequestBody Cozinha cozinha) {
        try {
            Cozinha cozinhaAtual = cadastroCozinhaService.atualizar(cozinhaId, cozinha);
            return ResponseEntity.ok(cozinhaAtual);
        }catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.notFound().build();
        }

    }

    @DeleteMapping("/{cozinhaId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long cozinhaId ) {
           cadastroCozinhaService.excluir(cozinhaId);
    }

}
