package com.example.algafood.api.controller;

import com.example.algafood.domain.exception.EstadoNaoEncontradoException;
import com.example.algafood.domain.exception.NegocioException;
import com.example.algafood.domain.model.Cidade;
import com.example.algafood.domain.service.CadastroCidadeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/cidades")
public class CidadeController {

    private CadastroCidadeService cadastroCidadeService;

    @GetMapping
    public List<Cidade> listar() {
        return cadastroCidadeService.listar();
    }

    @GetMapping("/{cidadeId}")
    public Cidade buscar(@PathVariable Long cidadeId) {
        Cidade cidade = cadastroCidadeService.buscarOuFahar(cidadeId);
        return cidade;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cidade adicionar(@Valid @RequestBody Cidade cidade) {
        try {
            return cadastroCidadeService.adicionar(cidade);
        }catch( EstadoNaoEncontradoException e ){
            throw new NegocioException(e.getMessage(), e);
        }
    }

    @PutMapping("/{cidadeId}")
    public Cidade atualizar(@PathVariable Long cidadeId ,@Valid @RequestBody Cidade cidade) {
        try {
            return  cadastroCidadeService.atualizar(cidadeId, cidade);
        }catch( EstadoNaoEncontradoException e){
            throw new NegocioException(e.getMessage(), e);
        }
    }

    @DeleteMapping("/{cidadeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long cidadeId ) {
            cadastroCidadeService.excluir(cidadeId);
    }

}
