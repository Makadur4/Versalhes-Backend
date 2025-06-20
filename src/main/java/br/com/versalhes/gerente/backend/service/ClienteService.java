package br.com.versalhes.gerente.backend.service;

import br.com.versalhes.gerente.backend.model.Cliente;
import br.com.versalhes.gerente.backend.model.ClienteRepository;

import br.com.versalhes.gerente.backend.model.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteService {

ClienteRepository _clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {

        _clienteRepository = clienteRepository;

    }

    @Transactional
    public Cliente IncluirCliente(Cliente cliente)
    {
        Cliente novoCliente = _clienteRepository.save(cliente);

        return novoCliente;

    }

    @Transactional
    public Cliente AtualizarCliente(Cliente cliente) throws Exception {
        Optional<Cliente> clienteExistente = _clienteRepository.findById(cliente.getIdCliente());

        if (clienteExistente.isPresent()) {

            Cliente clienteAtualizado = _clienteRepository.save(cliente);

            return clienteAtualizado;
        }
        throw new Exception();
    }

    public Cliente ValidarCLiente(String email, String senha) throws Exception {
        Optional<Cliente> clienteExistente = _clienteRepository.findByEmailAndSenha(email, senha);

        if (clienteExistente.isPresent()) {

            return clienteExistente.get();

        }

        throw new Exception();

    }

    public void RecuperarSenha(String email)
    {


    }

}
