package br.com.versalhes.backend.repositories;

import br.com.versalhes.backend.models.Cliente;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Optional<Cliente> findByEmailAndSenha(String email, String senha);
    Optional<Cliente> findByEmail(String email);
    Optional<Cliente> findByChave(UUID chave);
}
