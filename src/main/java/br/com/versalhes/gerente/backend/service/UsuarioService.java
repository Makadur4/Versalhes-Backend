package br.com.versalhes.gerente.backend.service;

import br.com.versalhes.gerente.backend.model.Usuario;
import br.com.versalhes.gerente.backend.model.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

    UsuarioRepository _usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {

        _usuarioRepository = usuarioRepository;

    }

    public Usuario ValidarUsuario(String login, String senha) throws Exception {

        Optional<Usuario> usuarioExistente = _usuarioRepository.findByLoginAndSenha(login, senha);

        if (usuarioExistente.isPresent()) {

            return usuarioExistente.get();

        }

        throw new Exception();

    }

}
