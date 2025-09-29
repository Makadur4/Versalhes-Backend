package br.com.versalhes.backend.repositories;

import br.com.versalhes.backend.models.Tipo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoRepository extends JpaRepository<Tipo, Long> { }
