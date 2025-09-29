package br.com.versalhes.backend.services;

import br.com.versalhes.backend.models.CondicaoPagamento;
import br.com.versalhes.backend.models.Frete;
import br.com.versalhes.backend.models.Marca;
import br.com.versalhes.backend.models.Tipo;

import br.com.versalhes.backend.repositories.CondicaoPagamentoRepository;
import br.com.versalhes.backend.repositories.FreteRepository;
import br.com.versalhes.backend.repositories.MarcaRepository;
import br.com.versalhes.backend.repositories.TipoRepository;

import br.com.versalhes.backend.utils.CepUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApoioService {
    @Autowired
    CondicaoPagamentoRepository _condicaoPagamentoRepository;

    @Autowired
    FreteRepository _freteRepository;

    @Autowired
    MarcaRepository _marcaRepository;

    @Autowired
    TipoRepository _tipoRepository;

    public List<Marca> obterMarcas() {
        return _marcaRepository.findAll();
    }

    public List<Tipo> obterTipos() {
        return _tipoRepository.findAll();
    }

    public List<CondicaoPagamento> obterCondicoesPagamento() {
        return _condicaoPagamentoRepository.findAll();
    }

    public List<Frete> obterFretesCep(String cep) {
        String uf = CepUtil.obterUf(cep);

        return _freteRepository.findByUf(uf);
    }
}