package br.com.versalhes.backend.controllers;

import br.com.versalhes.backend.models.Cliente;
import br.com.versalhes.backend.models.EnderecoCliente;

import br.com.versalhes.backend.security.SecurityUtil;

import br.com.versalhes.backend.services.ClienteService;

import br.com.versalhes.backend.security.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;
import java.util.UUID;

@RestController
@CrossOrigin(origins="*", maxAge = 3600, allowCredentials = "false")
@RequestMapping("/cliente")
public class ClienteController {
    @Autowired
    private ClienteService _clienteService;

    @PostMapping("incluir-cliente")
    public ResponseEntity<Cliente> incluirCliente(@RequestBody Cliente cliente) {
        try {
            Cliente novoCliente = _clienteService.incluirCliente(cliente);

            return ResponseEntity.status(HttpStatus.CREATED).body(novoCliente);
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("obter-cliente")
    public ResponseEntity<Cliente> obterCliente() {
        try {
            Long id = SecurityUtil.obterClienteId();

            Cliente clienteExistente = _clienteService.obterCliente(id != null ? id : 0);

            return ResponseEntity.status(HttpStatus.OK).body(clienteExistente);
        } catch(NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("alterar-cliente")
    public ResponseEntity<Cliente> alterarCliente(@RequestBody Cliente cliente) {
        try {
            Long clienteId = SecurityUtil.obterClienteId();

            cliente.setId(clienteId != null ? clienteId : 0);

            Cliente clienteAtualizado = _clienteService.alterarCliente(cliente);

            return ResponseEntity.status(HttpStatus.OK).body(clienteAtualizado);
        } catch(NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("validar-cliente")
    public ResponseEntity<String> validarCliente(@RequestHeader String email, @RequestHeader String senha) {
        try {
            Cliente clienteExistente = _clienteService.validarCLiente(email, senha);

            String token = JwtUtil.gerarToken("cliente", clienteExistente.getId());

            return ResponseEntity.status(HttpStatus.OK).body(token);
        } catch(NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PatchMapping("solicitar-recuperacao-senha")
    public ResponseEntity<Void> solicitarRecuperacaoSenha(@RequestBody SolicitarRecuperacaoSenhaRequest request){
        try {
            _clienteService.solicitarRecuperacaoSenha(request.email);

            return ResponseEntity.status(HttpStatus.OK).build();
        } catch(NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    public record SolicitarRecuperacaoSenhaRequest(String email) { }

    @PatchMapping("alterar-senha")
    public ResponseEntity<Void> alterarSenha(@RequestBody AlterarSenhaRequest request){
        try {
            UUID chave = request.chave!= null ? request.chave : UUID.randomUUID();

           _clienteService.alterarSenha(chave, request.senha);

            return ResponseEntity.status(HttpStatus.OK).build();
        } catch(NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    public record AlterarSenhaRequest(UUID chave, String senha) { }

    @GetMapping("obter-endereco-cliente")
    public ResponseEntity<EnderecoCliente> obterEnderecoCliente() {
        try {
            Long clienteId = SecurityUtil.obterClienteId();

            EnderecoCliente enderecoClienteExistente = _clienteService.obterEnderecoCliente(clienteId != null ? clienteId : 0);

            return ResponseEntity.status(HttpStatus.OK).body(enderecoClienteExistente);
        } catch(NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("alterar-endereco-cliente")
    public ResponseEntity<EnderecoCliente> alterarEnderecoCliente(@RequestBody EnderecoCliente enderecoCliente) {
        try {
            Long clienteId = SecurityUtil.obterClienteId();

            enderecoCliente.setClienteId(clienteId != null ? clienteId : 0);

            EnderecoCliente enderecoClienteAtualizado = _clienteService.alterarEnderecoCliente(enderecoCliente);

            return ResponseEntity.status(HttpStatus.OK).body(enderecoClienteAtualizado);
        } catch(NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
