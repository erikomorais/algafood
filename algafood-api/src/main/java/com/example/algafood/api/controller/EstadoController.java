package com.example.algafood.api.controller;

import com.example.algafood.domain.exception.EntidadeEmUsoException;
import com.example.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.example.algafood.domain.model.Estado;
import com.example.algafood.domain.service.CadastroEstadoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/estados")
@AllArgsConstructor
public class EstadoController {

    private CadastroEstadoService cadastroEstadoService;

    @GetMapping
    public ResponseEntity<List<Estado>> listar() {
        return ResponseEntity.ok(cadastroEstadoService.listar());
    }

    @GetMapping("/{estadoId}")
    public ResponseEntity<Estado> buscar(@PathVariable Long estadoId) {
        try{
            Estado estado = cadastroEstadoService.buscarOuFalhar(estadoId);
            return ResponseEntity.ok(estado);
        }  catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Estado adicionar(@Valid @RequestBody Estado estado) {
        estado = cadastroEstadoService.adicionar(estado);
        return estado;

    }

    @PutMapping("/{estadoId}")
    public ResponseEntity<Estado> atualizar(@PathVariable Long estadoId ,@Valid @RequestBody Estado estado) {
        try {
            Estado estadoAtual = cadastroEstadoService.atualizar(estadoId, estado);
            return ResponseEntity.ok(estadoAtual);
        }catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.notFound().build();
        }

    }

    @DeleteMapping("/{estadoId}")
    public ResponseEntity<Void> remover(@PathVariable Long estadoId ) {
        try {
            cadastroEstadoService.excluir(estadoId);
            return ResponseEntity.noContent().build();
        }catch (EntidadeEmUsoException e ){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

    }
}