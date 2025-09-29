package br.com.versalhes.backend.services;

import br.com.versalhes.backend.models.Usuario;

import br.com.versalhes.backend.repositories.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    @Autowired
    UsuarioRepository _usuarioRepository;

    public Usuario validarUsuario(String login, String senha) throws Exception {
        return _usuarioRepository.findByLoginAndSenha(login, senha).orElseThrow();
    }
}
