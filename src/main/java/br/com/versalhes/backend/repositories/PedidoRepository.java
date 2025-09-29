package br.com.versalhes.backend.repositories;

import br.com.versalhes.backend.models.Avaliacao;
import br.com.versalhes.backend.models.Pedido;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    List<Pedido> findByClienteId(long clienteId);
}
