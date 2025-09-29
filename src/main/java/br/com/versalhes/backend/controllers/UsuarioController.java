package br.com.versalhes.backend.controllers;

import br.com.versalhes.backend.models.Usuario;

import br.com.versalhes.backend.security.JwtUtil;
import br.com.versalhes.backend.services.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@CrossOrigin(origins="*", maxAge = 3600, allowCredentials = "false")
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService _usuarioService;

    @GetMapping("validar-usuario")
    public ResponseEntity<String> ValidarUsuario(@RequestHeader String login, @RequestHeader String senha) {
        try {
            Usuario usuarioExistente = _usuarioService.validarUsuario(login, senha);

            String token = JwtUtil.gerarToken(usuarioExistente.getId());

            return ResponseEntity.status(HttpStatus.OK).body(token);
        } catch(NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
