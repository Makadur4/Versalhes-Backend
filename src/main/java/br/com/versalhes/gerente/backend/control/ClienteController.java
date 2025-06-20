package br.com.versalhes.gerente.backend.control;

import br.com.versalhes.gerente.backend.model.Cliente;
import br.com.versalhes.gerente.backend.model.Perfume;
import br.com.versalhes.gerente.backend.model.Usuario;
import br.com.versalhes.gerente.backend.service.ClienteService;
import br.com.versalhes.gerente.backend.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins="*", maxAge = 3600, allowCredentials = "false")
@RequestMapping("/cliente")
public class ClienteController {

    private ClienteService _clienteService;

    public ClienteController(ClienteService clienteService) {

        _clienteService = clienteService;

    }

    @PostMapping()
    public ResponseEntity<Object> IncluirCliente(@RequestBody Cliente cliente)
    {

        Cliente novoCliente = _clienteService.IncluirCliente(cliente);

        return ResponseEntity.status(HttpStatus.CREATED).body(novoCliente);

    }

    @PutMapping
    public ResponseEntity<Object> AtualizarCliente(@RequestBody Cliente cliente) {

        try {

            Cliente clienteAtualizado = _clienteService.AtualizarCliente(cliente);

            return ResponseEntity.status(HttpStatus.OK).body(clienteAtualizado);

        } catch (Exception ex) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        }

    }

    @GetMapping()
    public ResponseEntity<Cliente> ValidarCliente(@RequestParam String email, @RequestParam String senha)
    {

        try {

            Cliente clienteExistente = _clienteService.ValidarCLiente(email, senha);

            return ResponseEntity.status(HttpStatus.OK).body(clienteExistente);

        } catch (Exception ex) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        }

    }

    @PostMapping("/senha")
    public ResponseEntity<Void> RecuperarSenha(@RequestParam String email){

        try {

            _clienteService.RecuperarSenha(email);

            return ResponseEntity.status(HttpStatus.OK).build();

        } catch (Exception ex) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        }

    }

}
