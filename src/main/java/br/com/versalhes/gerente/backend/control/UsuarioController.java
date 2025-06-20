package br.com.versalhes.gerente.backend.control;

import br.com.versalhes.gerente.backend.model.Usuario;
import br.com.versalhes.gerente.backend.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins="*", maxAge = 3600, allowCredentials = "false")
@RequestMapping("/usuario")
public class UsuarioController {

    private UsuarioService _usuarioService;

    public UsuarioController(UsuarioService usuarioService) {

        _usuarioService = usuarioService;

    }

    @GetMapping()
    public ResponseEntity<Usuario> ValidarUsuario(@RequestParam String login, @RequestParam String senha)
    {

        try {

            Usuario usuarioExistente = _usuarioService.ValidarUsuario(login, senha);

            return ResponseEntity.status(HttpStatus.OK).body(usuarioExistente);

        } catch (Exception ex) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        }

    }

}
