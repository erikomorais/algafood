package com.example.algafood.domain.service;

import com.example.algafood.domain.exception.*;
import com.example.algafood.domain.model.Cidade;
import com.example.algafood.domain.model.Estado;
import com.example.algafood.domain.repository.CidadeRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CadastroCidadeService {

    public static final String ESTADO_NAO_INFORMADO = "Estado não informado";
    public static final String CIDADE_EM_USO = "Cidade com código %s está em uso";
    private CidadeRepository cidadeRepository;
    private CadastroEstadoService cadastroEstadoService;
    public List<Cidade> listar() {
        return cidadeRepository.findAll();
    }

    public Cidade buscarOuFahar(Long cidadeId) {
        return cidadeRepository.findById(cidadeId)
                .orElseThrow(()->new CidadeNaoEncontradaException(cidadeId));
    }

    public Cidade adicionar(Cidade cidade) {
        if (cidade.getEstado() == null ){
            throw new EstadoNaoInformadoException(ESTADO_NAO_INFORMADO);
        }
        Long estadoId = cidade.getEstado().getId();
        Estado estado = cadastroEstadoService.buscarOuFalhar(estadoId);
        cidade.setEstado(estado);
        return cidadeRepository.save(cidade);
    }

    public Cidade atualizar(Long cidadeId, Cidade cidade) {
        Cidade cidadeAtualizar = buscarOuFahar(cidadeId);
        if (cidade.getEstado() == null ){
            throw new EstadoNaoInformadoException(ESTADO_NAO_INFORMADO);
        }
        BeanUtils.copyProperties(cidade, cidadeAtualizar, "id");
        Estado estado = cadastroEstadoService.buscarOuFalhar(cidade.getEstado().getId());
        cidade.setEstado(estado);
        return cidadeRepository.save(cidadeAtualizar);

    }
    public void excluir(Long cidadeId) {
        try{
            cidadeRepository.deleteById(cidadeId);
        }catch (DataIntegrityViolationException e){
            throw new EntidadeEmUsoException(String.format(CIDADE_EM_USO,cidadeId));
        }catch (EmptyResultDataAccessException e){
            throw new CidadeNaoEncontradaException(cidadeId);
        }
    }
}