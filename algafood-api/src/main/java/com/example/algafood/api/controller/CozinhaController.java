package com.example.algafood.api.controller;

import com.example.algafood.api.model.CozinhasXmlWrapper;
import com.example.algafood.domain.exception.EntidadeEmUsoException;
import com.example.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.example.algafood.domain.model.Cozinha;
import com.example.algafood.domain.repository.CozinhaRepository;
import com.example.algafood.domain.service.CadastroCozinhaService;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

    @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
    public CozinhasXmlWrapper listarXml() {
        return new CozinhasXmlWrapper(cadastroCozinhaService.listar());
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
    public ResponseEntity<Void> remover(@PathVariable Long cozinhaId ) {
        try {
            cadastroCozinhaService.excluir(cozinhaId);
            return ResponseEntity.noContent().build();
        }catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.notFound().build();
        }catch (EntidadeEmUsoException e ){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

    }


}
