package br.com.versalhes.backend.repositories;

import br.com.versalhes.backend.models.DadosPagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DadosPagamentoRepository extends JpaRepository<DadosPagamento, Long> {
}
