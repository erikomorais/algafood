package com.example.algafood.api.controller;

import com.example.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.example.algafood.domain.exception.EstadoInexistenteException;
import com.example.algafood.domain.exception.EstadoNaoInformadoException;
import com.example.algafood.domain.model.Cidade;
import com.example.algafood.domain.service.CadastroCidadeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/cidades")
public class CidadeController {

    private CadastroCidadeService cadastroCidadeService;

    @GetMapping
    public ResponseEntity<List<Cidade>> listar() {
        return ResponseEntity.ok(cadastroCidadeService.listar());
    }

    @GetMapping("/{cidadeId}")
    public ResponseEntity<Cidade> buscar(@PathVariable Long cidadeId) {
        try{
            Cidade cidade = cadastroCidadeService.buscar(cidadeId);
            return ResponseEntity.ok(cidade);
        }  catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> adicionar(@RequestBody Cidade cidade) {
        try {
            cidade = cadastroCidadeService.adicionar(cidade);
            return ResponseEntity.ok(cidade);
        }catch (EstadoNaoInformadoException | EstadoInexistenteException e){
            return  ResponseEntity.badRequest().body(e.getMessage());
        }


    }

    @PutMapping("/{cidadeId}")
    public Cidade atualizar(@PathVariable Long cidadeId ,@RequestBody Cidade cidade) {
            return  cadastroCidadeService.atualizar(cidadeId, cidade);

    }

    @DeleteMapping("/{cidadeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long cidadeId ) {
            cadastroCidadeService.excluir(cidadeId);
    }
}
