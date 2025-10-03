package br.com.versalhes.backend.repositories;

import br.com.versalhes.backend.models.Cliente;
import br.com.versalhes.backend.models.Favorito;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FavoritoRepository extends JpaRepository<Favorito, Long> {
    List<Favorito> findByClienteId(long clienteId);
    Optional<Favorito> findByPerfumeIdAndClienteId(long clienteId, long perfumeId);
}
