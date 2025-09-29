package br.com.versalhes.backend.repositories;

import br.com.versalhes.backend.models.EnderecoCliente;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoClienteRepository extends JpaRepository<EnderecoCliente, Long> { }
