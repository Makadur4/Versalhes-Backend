package br.com.versalhes.backend.repositories;

import br.com.versalhes.backend.models.EnderecoEntrega;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoEntregaRepository extends JpaRepository<EnderecoEntrega, Long> { }
