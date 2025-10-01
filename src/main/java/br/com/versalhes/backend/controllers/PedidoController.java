package br.com.versalhes.backend.controllers;

import br.com.versalhes.backend.models.*;

import br.com.versalhes.backend.security.SecurityUtil;

import br.com.versalhes.backend.services.PedidoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@CrossOrigin(origins="*", maxAge = 3600, allowCredentials = "false")
@RequestMapping("/pedido")
public class PedidoController {
    @Autowired
    private PedidoService _pedidoService;

    @PostMapping("incluir-pedido")
    public ResponseEntity<Pedido> incluirPedido(@RequestBody IncluirPedidoRequest request) {
        try {
            Long clienteId = SecurityUtil.obterClienteId();

            Pedido novoPedido = _pedidoService.incluirPedido(clienteId != null ? clienteId : 0, request.freteId, request.dadosPagamento, request.itensPedido);

            return ResponseEntity.status(HttpStatus.CREATED).body(novoPedido);
        } catch(NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    public record IncluirPedidoRequest(long freteId, DadosPagamento dadosPagamento, List<ItemPedido> itensPedido) {}

    @GetMapping("obter-pedidos")
    public ResponseEntity<List<Pedido>> obterPedidos() {
        try {
            Long clienteId = SecurityUtil.obterClienteId();

            List<Pedido> lista = _pedidoService.obterPedidos(clienteId != null ? clienteId : 0);

            return ResponseEntity.status(HttpStatus.OK).body(lista);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("obter-pedido/{id}")
    public ResponseEntity<Pedido> obterPedido(@PathVariable("id") long id) {
        try {
            Long clienteId = SecurityUtil.obterClienteId();

            Pedido pedidoExistente = _pedidoService.obterPedido(id, clienteId != null ? clienteId : 0);

            return ResponseEntity.status(HttpStatus.OK).body(pedidoExistente);
        } catch(NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PatchMapping("cancelar-pedido/{id}")
    public ResponseEntity<Pedido> cancelarPedido(@PathVariable("id") long id) {
        try {
            Long clienteId = SecurityUtil.obterClienteId();

            _pedidoService.cancelarPedido(id, clienteId != null ? clienteId : 0);

            return ResponseEntity.status(HttpStatus.OK).build();
        } catch(NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
