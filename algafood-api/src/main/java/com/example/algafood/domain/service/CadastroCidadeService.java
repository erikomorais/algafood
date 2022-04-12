package com.example.algafood.domain.service;

import com.example.algafood.domain.exception.EntidadeEmUsoException;
import com.example.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.example.algafood.domain.exception.EstadoInexistenteException;
import com.example.algafood.domain.exception.EstadoNaoInformadoException;
import com.example.algafood.domain.model.Cidade;
import com.example.algafood.domain.model.Estado;
import com.example.algafood.domain.repository.CidadeRepository;
import com.example.algafood.domain.repository.EstadoRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CadastroCidadeService {
    private CidadeRepository cidadeRepository;
    private EstadoRepository estadoRepository;
    public List<Cidade> listar() {
        return cidadeRepository.findAll();
    }

    public Cidade buscar(Long cidadeId) {
        return cidadeRepository.findById(cidadeId)
                .orElseThrow(()->new EntidadeNaoEncontradaException(
                        String.format("Cidade com coódigo %d não encontrada", cidadeId)));
    }

    public Cidade adicionar(Cidade cidade) {
        if (cidade.getEstado()== null ){
            throw new EstadoNaoInformadoException("Estado não informado");
        }
        Long estadoId = cidade.getEstado().getId();
        Estado estado = estadoRepository.findById(estadoId)
                .orElseThrow(()->new EstadoInexistenteException(
                        String.format("Não existe cadastro de estado com código %d", estadoId)));
        cidade.setEstado(estado);
        return cidadeRepository.save(cidade);
    }

    public Cidade atualizar(Long cidadeId, Cidade cidade) {
        Cidade cidadeAtualizar = buscar(cidadeId);
        if (cidade.getEstado() == null ){
            throw new EstadoNaoInformadoException("Estado não pode ser nulo");
        }
        Long estadoId = cidade.getEstado().getId();
        Estado estado = estadoRepository.findById(estadoId).orElseThrow(()->new EstadoInexistenteException(
                String.format("Não existe cadastro de estado com código %d", estadoId)));

        cidade.setEstado(estado);
        BeanUtils.copyProperties(cidade,cidadeAtualizar, "id");
        return cidadeRepository.save(cidadeAtualizar);

    }

    public void excluir(Long cidadeId) {
        try{
            cidadeRepository.deleteById(cidadeId);
        }catch (DataIntegrityViolationException e){
            throw new EntidadeEmUsoException(String.format("Estado com dódigo %s",cidadeId));
        }catch (EmptyResultDataAccessException e){
            throw new EntidadeNaoEncontradaException(String.format("Estado com dódigo %s não encontrada",cidadeId));
        }
    }
}
