package com.example.algafood.domain.service;

import com.example.algafood.api.model.CozinhasXmlWrapper;
import com.example.algafood.domain.model.Cozinha;
import com.example.algafood.domain.repository.CozinhaRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
        Cozinha cozinha = cozinhaRepository.buscar(cozinhaId);
            return cozinha;
    }

    public Cozinha adicionar(Cozinha cozinha) {
        cozinha = cozinhaRepository.salvar(cozinha);
        return cozinha;
    }

    public ResponseEntity<Void> excluir(Long cozinhaId ) {
        try {
            Cozinha cozinhaAtual = cozinhaRepository.buscar(cozinhaId);
            if (cozinhaAtual != null) {
                cozinhaRepository.remover(cozinhaAtual);
                return ResponseEntity.noContent().build();
            }else {
                return ResponseEntity.notFound().build();
            }
        }catch (DataIntegrityViolationException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

    }

    @PutMapping("/{cozinhaId}")
    public ResponseEntity<Cozinha> atualizar(@PathVariable Long cozinhaId ,@RequestBody Cozinha cozinha) {
        Cozinha cozinhaAtual = cozinhaRepository.buscar(cozinhaId);
        if(cozinhaAtual!=null) {
            BeanUtils.copyProperties(cozinha, cozinhaAtual, "id");
            cozinhaRepository.salvar(cozinhaAtual);
            return ResponseEntity.ok(cozinhaAtual);
        }
        return ResponseEntity.notFound().build();

    }


}
