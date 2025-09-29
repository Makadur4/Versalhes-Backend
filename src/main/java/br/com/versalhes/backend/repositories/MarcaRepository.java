package br.com.versalhes.backend.repositories;

import br.com.versalhes.backend.models.Marca;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarcaRepository extends JpaRepository<Marca, Long> { }
