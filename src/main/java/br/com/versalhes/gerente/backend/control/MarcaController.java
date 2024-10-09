package br.com.versalhes.gerente.backend.control;

import br.com.versalhes.gerente.backend.model.Marca;
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
@RequestMapping("/marca")
public class MarcaController {

    CadastroService _cadastroService;

    public MarcaController(CadastroService cadastroService) {

        _cadastroService = cadastroService;

    }

    @GetMapping
    public ResponseEntity<List<Marca>> ConsultarMarcaTodos() {

        List<Marca> lista = _cadastroService.ConsultarMarcaTodas();

        return ResponseEntity.status(HttpStatus.OK).body(lista);

    }

}
