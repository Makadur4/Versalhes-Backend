package br.com.versalhes.gerente.backend.control;

import br.com.versalhes.gerente.backend.model.Perfume;
import br.com.versalhes.gerente.backend.service.CadastroService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins="*", maxAge = 3600, allowCredentials = "false")
@RequestMapping("/perfume")
public class PerfumeController {

    CadastroService _cadastroService;

    public PerfumeController(CadastroService cadastroService) {

        _cadastroService = cadastroService;

    }

    @PostMapping
    public ResponseEntity<Object> IncluirPerfume(@RequestBody Perfume perfume) {

        Perfume novoPerfume = _cadastroService.IncluirPerfume(perfume);

        return ResponseEntity.status(HttpStatus.CREATED).body(novoPerfume);

    }

    @PutMapping
    public ResponseEntity<Object> AtualizarPerfume(@RequestBody Perfume perfume) {

        try {

            Perfume perfumeAtualizado = _cadastroService.AtualizarPerfume(perfume);

            return ResponseEntity.status(HttpStatus.OK).body(perfumeAtualizado);

        } catch (Exception ex) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        }

    }

    @DeleteMapping("/{idPerfume}")
    public ResponseEntity<Void> ExcluirPerfume(@PathVariable("idPerfume") long idPerfume) {

        try {

            _cadastroService.ExcluirPerfume(idPerfume);

            return ResponseEntity.status(HttpStatus.OK).build();

        } catch (Exception ex) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        }

    }

    @GetMapping
    public ResponseEntity<List<Perfume>> ConsultarPerfumeTodos() {

        List<Perfume> lista = _cadastroService.ConsultarPerfumeTodos();

        return ResponseEntity.status(HttpStatus.OK).body(lista);

    }

    @GetMapping("/{idPerfume}")
    public ResponseEntity<Perfume> ConsultarPerfumePeloId(@PathVariable("idPerfume") long idPerfume) {

        try {

            Perfume perfumeExistente = _cadastroService.ConsultarPerfumePeloId(idPerfume);

            return ResponseEntity.status(HttpStatus.OK).body(perfumeExistente);

        } catch (Exception ex) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        }

    }

    @PatchMapping("/{idPerfume}")
    public ResponseEntity<Object> AtualizarEstoque(@PathVariable("idPerfume") long idPerfume, @RequestBody Map<String, Object> campos) {

        try {

            if (campos.containsKey("estoque") == false) {

                throw new Exception();

            }

            Long estoque = ((Integer) (campos.get("estoque"))).longValue();

            Perfume perfumeAtualizado = _cadastroService.AtualizarEstoque(idPerfume, estoque);

            return ResponseEntity.status(HttpStatus.OK).body(perfumeAtualizado);

        } catch (Exception ex) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        }

    }

}
