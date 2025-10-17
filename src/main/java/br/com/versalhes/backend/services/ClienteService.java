package br.com.versalhes.backend.services;

import br.com.versalhes.backend.models.Cliente;
import br.com.versalhes.backend.models.EnderecoCliente;

import br.com.versalhes.backend.repositories.ClienteRepository;

import br.com.versalhes.backend.repositories.EnderecoClienteRepository;
import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class ClienteService {
    @Autowired
    ClienteRepository _clienteRepository;

    @Autowired
    EnderecoClienteRepository _enderecoClienteRepository;

    @Transactional
    public Cliente incluirCliente(Cliente cliente) {
        cliente.setEmail( cliente.getEmail().toLowerCase(Locale.ROOT));

        Cliente novoCliente = _clienteRepository.save(cliente);

        EnderecoCliente enderecoCliente = new EnderecoCliente();
        enderecoCliente.setClienteId(novoCliente.getId());

        _enderecoClienteRepository.save(enderecoCliente);

        return novoCliente;
    }

    public Cliente obterCliente(long id) throws Exception {
        return _clienteRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Transactional
    public Cliente alterarCliente(Cliente cliente) throws Exception {
        Cliente clienteExistente = _clienteRepository.findById(cliente.getId()).orElseThrow();

        cliente.setEmail( cliente.getEmail().toLowerCase(Locale.ROOT));
        cliente.setSenha(clienteExistente.getSenha());

        return _clienteRepository.save(cliente);
    }

    public Cliente validarCLiente(String email, String senha) throws Exception {
        return _clienteRepository.findByEmailAndSenha(email.toLowerCase(Locale.ROOT), senha).orElseThrow();
    }

    @Transactional
    public void solicitarRecuperacaoSenha(String email){
        Cliente clienteExistente = _clienteRepository.findByEmail(email.toLowerCase(Locale.ROOT)).orElse(null);

        if(clienteExistente == null) { return; }

        clienteExistente.setChave(UUID.randomUUID());

        _clienteRepository.save(clienteExistente);
    }

    @Transactional
    public void alterarSenha(UUID chave, String senha){
        Cliente clienteExistente = _clienteRepository.findByChave(chave).orElseThrow();

        clienteExistente.setChave(null);
        clienteExistente.setSenha(senha);

        _clienteRepository.save(clienteExistente);
    }

    public EnderecoCliente obterEnderecoCliente(long id) throws Exception {
        return _enderecoClienteRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Transactional
    public EnderecoCliente alterarEnderecoCliente(EnderecoCliente enderecoCliente) throws Exception {
        _enderecoClienteRepository.findById(enderecoCliente.getClienteId()).orElseThrow();

        return _enderecoClienteRepository.save(enderecoCliente);
    }
}
