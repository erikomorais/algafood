package com.example.algafood.domain.service;

import com.example.algafood.domain.exception.EntidadeEmUsoException;
import com.example.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.example.algafood.domain.model.Estado;
import com.example.algafood.domain.repository.EstadoRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;


@Service
@AllArgsConstructor
public class CadastroEstadoService {

    private EstadoRepository estadoRepository;


    public List<Estado> listar() {
        return estadoRepository.findAll();
    }

    public Estado buscar(Long estadoId) {

        return estadoRepository.findById(estadoId).orElseThrow( ()->
                new EntidadeNaoEncontradaException(String.format("Estado de id %s não encontrado", estadoId))
        );
    }

    public Estado adicionar(Estado estado) {
        estado = estadoRepository.save(estado);
        return estado;
    }

    public Estado atualizar(@PathVariable Long estadoId ,@RequestBody Estado estado) {
        Estado estadoAtual = buscar(estadoId);

        BeanUtils.copyProperties(estado, estadoAtual, "id");
        return estadoRepository.save(estadoAtual);
    }

    public void excluir(Long estadoId ) {
        try{
            estadoRepository.deleteById(estadoId);
        }catch (DataIntegrityViolationException e){
            throw new EntidadeEmUsoException(String.format("Estado com dódigo %s",estadoId));
        }catch (EmptyResultDataAccessException e){
            throw new EntidadeNaoEncontradaException(String.format("Estado com dódigo %s não encontrada",estadoId));
        }

    }




}
