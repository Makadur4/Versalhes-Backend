package br.com.versalhes.backend.repositories;

import br.com.versalhes.backend.models.Avaliacao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long> {
    List<Avaliacao> findByPerfumeId(long perfumeId);
    List<Avaliacao> findByClienteId(long clienteId);
}
