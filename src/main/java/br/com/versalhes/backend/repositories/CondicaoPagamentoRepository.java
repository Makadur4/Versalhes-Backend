package br.com.versalhes.backend.repositories;

import br.com.versalhes.backend.models.CondicaoPagamento;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CondicaoPagamentoRepository extends JpaRepository<CondicaoPagamento, Long> { }
