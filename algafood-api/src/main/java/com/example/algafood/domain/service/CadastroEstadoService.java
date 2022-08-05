package com.example.algafood.domain.service;

import com.example.algafood.domain.exception.EntidadeEmUsoException;
import com.example.algafood.domain.exception.EstadoNaoEncontradoException;
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
    public static final String ESTADO_EM_USO = "Estado com código %s em uso";
    private EstadoRepository estadoRepository;
    public List<Estado> listar() {
        return estadoRepository.findAll();
    }

    public Estado buscarOuFalhar(Long estadoId) {
        return estadoRepository.findById(estadoId).orElseThrow( ()->
            new EstadoNaoEncontradoException(estadoId)
        );
    }

    public Estado adicionar(Estado estado) {
        estado = estadoRepository.save(estado);
        return estado;
    }

    public Estado atualizar(@PathVariable Long estadoId ,@RequestBody Estado estado) {
        Estado estadoAtual = buscarOuFalhar(estadoId);
        BeanUtils.copyProperties(estado, estadoAtual, "id");
        return estadoRepository.save(estadoAtual);
    }

    public void excluir(Long estadoId ) {
        try{
            estadoRepository.deleteById(estadoId);
        }catch (DataIntegrityViolationException e){
            throw new EntidadeEmUsoException(String.format(ESTADO_EM_USO,estadoId));
        }catch (EmptyResultDataAccessException e){
            throw new EstadoNaoEncontradoException(estadoId);
        }

    }




}
