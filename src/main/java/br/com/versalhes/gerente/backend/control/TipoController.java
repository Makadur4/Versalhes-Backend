package br.com.versalhes.gerente.backend.control;

import br.com.versalhes.gerente.backend.model.Tipo;
import br.com.versalhes.gerente.backend.service.CadastroService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins="*", maxAge = 3600, allowCredentials = "false")
@RequestMapping("/tipo")
public class TipoController {

    CadastroService _cadastroService;

    public TipoController(CadastroService cadastroService) {

        _cadastroService = cadastroService;

    }

    @GetMapping
    public ResponseEntity<List<Tipo>> ConsultarMarcaTodos() {

        List<Tipo> lista = _cadastroService.ConsultarTipoTodos();

        return ResponseEntity.status(HttpStatus.OK).body(lista);

    }

}
