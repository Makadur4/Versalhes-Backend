package br.com.versalhes.backend.services;

import br.com.versalhes.backend.models.Favorito;

import br.com.versalhes.backend.models.Perfume;
import br.com.versalhes.backend.repositories.FavoritoRepository;
import br.com.versalhes.backend.repositories.PerfumeRepository;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class FavoritoService {
    @Autowired
    FavoritoRepository _favoritoRepository;

    @Autowired
    PerfumeRepository _perfumeRepository;

    @Transactional
    public void incluirFavorito(Favorito favorito) throws Exception {
        _perfumeRepository.findById(favorito.getPerfume().getId()).orElseThrow();

        _favoritoRepository.save(favorito);
    }

    public Optional<Favorito> obterFavorito(long perfumeId, long clienteId) throws Exception {
        return _favoritoRepository.findByPerfumeIdAndClienteId(perfumeId, clienteId);
    }

    @Transactional
    public void excluirFavorito(long id, long clienteId) throws Exception {
        Favorito favoritoExistente = _favoritoRepository.findById(id).orElseThrow();

        if(favoritoExistente.getCliente().getId() != clienteId) { throw new NoSuchElementException(); }

        _favoritoRepository.deleteById(favoritoExistente.getId());
    }
}