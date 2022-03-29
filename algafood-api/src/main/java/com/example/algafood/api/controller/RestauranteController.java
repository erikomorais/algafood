package com.example.algafood.api.controller;

import com.example.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.example.algafood.domain.model.Restaurante;
import com.example.algafood.domain.service.CadastroRestauranteService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurantes")
@AllArgsConstructor
public class RestauranteController {


    private CadastroRestauranteService cadastroRestaurante;

    @GetMapping
    public ResponseEntity<List<Restaurante>> listar(){
      return ResponseEntity.ok(cadastroRestaurante.listar());
    }

    @GetMapping("/{idRestaurante}")
    public ResponseEntity<Restaurante> buscar(@PathVariable Long idRestaurante){
        return ResponseEntity.ok(cadastroRestaurante.buscar(idRestaurante));
    }

    @PostMapping
    public ResponseEntity<?> adicionar(@RequestBody Restaurante restaurante){
        try {
            restaurante = cadastroRestaurante.adicionar(restaurante);
            return ResponseEntity.status(HttpStatus.CREATED).body(restaurante);
        }catch (EntidadeNaoEncontradaException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{idRestaurante}")
    public ResponseEntity<?> atualizar( @PathVariable Long idRestaurante, @RequestBody Restaurante restaurante){
        try {
            restaurante = cadastroRestaurante.atualizar(idRestaurante, restaurante);
            return ResponseEntity.status(HttpStatus.OK).body(restaurante);
        }catch (EntidadeNaoEncontradaException | IllegalStateException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @DeleteMapping("/{idRestaurante}")
    public ResponseEntity<Void> excluir(@PathVariable Long idRestaurante){
        try {
            cadastroRestaurante.excluir(idRestaurante);
            return ResponseEntity.noContent().build();
        }catch(EntidadeNaoEncontradaException e){
            return ResponseEntity.notFound().build();

        }
    }
}
