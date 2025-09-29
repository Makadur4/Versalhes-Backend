package br.com.versalhes.backend.repositories;

import br.com.versalhes.backend.models.Frete;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FreteRepository extends JpaRepository<Frete, Long> {
    List<Frete> findByUf(String uf);
}
