package com.example.algafood.api.controller;

import com.example.algafood.domain.exception.CozinhaNaoEncontradaException;
import com.example.algafood.domain.exception.NegocioException;
import com.example.algafood.domain.model.Restaurante;
import com.example.algafood.domain.service.CadastroRestauranteService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
    public Restaurante buscar(@PathVariable Long idRestaurante){
       return cadastroRestaurante.buscar(idRestaurante);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Restaurante adicionar(@RequestBody Restaurante restaurante){
       try {
           return cadastroRestaurante.adicionar(restaurante);
       }catch (CozinhaNaoEncontradaException e){
           throw new NegocioException(e.getMessage());
       }
    }

    @PutMapping("/{idRestaurante}")
    public Restaurante atualizar( @PathVariable Long idRestaurante, @RequestBody Restaurante restaurante){
        try {
           return cadastroRestaurante.atualizar(idRestaurante, restaurante);
        }catch (CozinhaNaoEncontradaException e){
            throw new NegocioException(e.getMessage());
        }
    }


    @DeleteMapping("/{idRestaurante}")
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void excluir(@PathVariable Long idRestaurante){
       cadastroRestaurante.excluir(idRestaurante);
    }

    @PatchMapping("/{idRestaurante}")
    public Restaurante atualizarParcial( @PathVariable Long idRestaurante, @RequestBody Map<String,Object> campos){
       return cadastroRestaurante.atualizarParcial(idRestaurante, campos);
    }
}
