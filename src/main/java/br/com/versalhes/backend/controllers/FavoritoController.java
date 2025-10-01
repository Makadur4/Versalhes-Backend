package br.com.versalhes.backend.controllers;

import br.com.versalhes.backend.models.Cliente;
import br.com.versalhes.backend.models.Favorito;
import br.com.versalhes.backend.models.Perfume;

import br.com.versalhes.backend.security.SecurityUtil;

import br.com.versalhes.backend.services.FavoritoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600, allowCredentials = "false")
@RequestMapping("/favorito")
public class FavoritoController {
    @Autowired
    FavoritoService _favoritoService;

    @PostMapping("incluir-favorito")
    public ResponseEntity<Void> incluirFavorito(@RequestBody IncluirFavoritoRequest request) {
        try {
            Long clienteId = SecurityUtil.obterClienteId();

            Perfume perfume = new Perfume();
            perfume.setId(request.perfumeId());

            Cliente cliente = new Cliente();
            cliente.setId(clienteId != null ? clienteId: 0);

            Favorito favorito = new Favorito();
            favorito.setPerfume(perfume);
            favorito.setCliente(cliente);

            _favoritoService.incluirFavorito(favorito);

            return ResponseEntity.status(HttpStatus.OK).build();
        } catch(NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    public record IncluirFavoritoRequest(Long perfumeId) { }

    @GetMapping("obter-favorito/{perfumeId}")
    public ResponseEntity<Favorito> obterAvaliacoesPerfume(@PathVariable("perfumeId") long perfumeId) {
        try {
            Long clienteId = SecurityUtil.obterClienteId();

            Favorito favorito = _favoritoService.obterFavorito(perfumeId, clienteId != null ? clienteId : 0).orElse(new Favorito());

            return ResponseEntity.status(HttpStatus.OK).body(favorito);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("excluir-favorito/{id}")
    public ResponseEntity<Void> excluirAvaliacao(@PathVariable("id") long id) {
        try {
            Long clienteId = SecurityUtil.obterClienteId();

            _favoritoService.excluirFavorito(id, clienteId != null ? clienteId : 0);

            return ResponseEntity.status(HttpStatus.OK).build();
        } catch(NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}