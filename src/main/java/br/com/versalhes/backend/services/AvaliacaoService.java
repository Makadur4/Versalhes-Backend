package br.com.versalhes.backend.services;

import br.com.versalhes.backend.models.Avaliacao;
import br.com.versalhes.backend.models.Perfume;

import br.com.versalhes.backend.repositories.AvaliacaoRepository;
import br.com.versalhes.backend.repositories.PerfumeRepository;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class AvaliacaoService {
    @Autowired
    AvaliacaoRepository _avaliacaoRepository;

    @Autowired
    PerfumeRepository _perfumeRepository;

    @Transactional
    public void incluirAvaliacao(Avaliacao avaliacao) throws Exception {
        Perfume perfumeExistente = _perfumeRepository.findById(avaliacao.getPerfume().getId()).orElseThrow();

        List<Avaliacao> avaliacoes = perfumeExistente.getAvaliacoes();
        List<Integer> pontuacoes = new java.util.ArrayList<>(avaliacoes.stream().map(Avaliacao::getAvaliacao).toList());

        pontuacoes.add(avaliacao.getAvaliacao());

        double media = pontuacoes.stream().mapToInt(Integer::intValue).average().orElse(3);

        perfumeExistente.setMediaAvaliacao((short) media);

        _perfumeRepository.save(perfumeExistente);
        _avaliacaoRepository.save(avaliacao);
    }

    public List<Avaliacao> obterAvaliacoesPerfume(long perfumeId) throws Exception {
        return _avaliacaoRepository.findByPerfumeId(perfumeId);
    }

    public List<Avaliacao> obterAvaliacoesCliente(long clienteId) throws Exception {
        return _avaliacaoRepository.findByClienteId(clienteId);
    }

    @Transactional
    public void excluirAvaliacao(long id, long clienteId) throws Exception {
        Avaliacao avaliacaoExistente = _avaliacaoRepository.findById(id).orElseThrow();

        if(avaliacaoExistente.getCliente().getId() != clienteId)
        {
            throw new NoSuchElementException();
        }

        _avaliacaoRepository.deleteById(avaliacaoExistente.getId());
    }
}