package br.com.versalhes.backend.controllers;

import br.com.versalhes.backend.models.*;

import br.com.versalhes.backend.services.ApoioService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600, allowCredentials = "false")
@RequestMapping("/apoio")
public class ApoioController {

    @Autowired
    ApoioService _apoioService;

    @GetMapping("obter-marcas")
    public ResponseEntity<List<Marca>> obterMarcas() {
        try {
            List<Marca> lista = _apoioService.obterMarcas();

            return ResponseEntity.status(HttpStatus.OK).body(lista);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("obter-tipos")
    public ResponseEntity<List<Tipo>> obterTipos() {
        try {
            List<Tipo> lista = _apoioService.obterTipos();

            return ResponseEntity.status(HttpStatus.OK).body(lista);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("obter-condicoes-pagamento")
    public ResponseEntity<List<CondicaoPagamento>> obterCondicoesPagamento() {
        try {
            List<CondicaoPagamento> lista = _apoioService.obterCondicoesPagamento();

            return ResponseEntity.status(HttpStatus.OK).body(lista);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("obter-fretes-cep/{cep}")
    public ResponseEntity<List<Frete>> obterFretesCep(@PathVariable("cep") String cep) {
        try {
            List<Frete> lista = _apoioService.obterFretesCep(cep);

            return ResponseEntity.status(HttpStatus.OK).body(lista);
        } catch(NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}