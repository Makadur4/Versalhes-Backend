package br.com.versalhes.backend.controllers;

import br.com.versalhes.backend.models.Avaliacao;
import br.com.versalhes.backend.models.Cliente;
import br.com.versalhes.backend.models.Perfume;

import br.com.versalhes.backend.security.SecurityUtil;

import br.com.versalhes.backend.services.AvaliacaoService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.dao.DataIntegrityViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600, allowCredentials = "false")
@RequestMapping("/avaliacao")
public class AvaliacaoController {

    @Autowired
    AvaliacaoService _avaliacaoService;

    @PostMapping("incluir-avaliacao")
    public ResponseEntity<Void> incluirAvaliacao(@RequestBody IncluirAvaliacaoRequest request) {
        try {
            Long clienteId = SecurityUtil.obterClienteId();

            Perfume perfume = new Perfume();
            perfume.setId(request.perfumeId());

            Cliente cliente = new Cliente();
            cliente.setId(clienteId != null ? clienteId: 0);

            Avaliacao avaliacao = new Avaliacao();
            avaliacao.setPerfume(perfume);
            avaliacao.setCliente(cliente);
            avaliacao.setDescricao(request.descricao());
            avaliacao.setAvaliacao(request.pontuacao());

            _avaliacaoService.incluirAvaliacao(avaliacao);

            return ResponseEntity.status(HttpStatus.OK).build();
        } catch(NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    public record IncluirAvaliacaoRequest(Long perfumeId, String descricao, int pontuacao) { }

    @GetMapping("obter-avaliacoes-perfume/{perfumeId}")
    public ResponseEntity<List<AvaliacaoResponse>> obterAvaliacoesPerfume(@PathVariable("perfumeId") long perfumeId) {
        try {
            List<Avaliacao> lista = _avaliacaoService.obterAvaliacoesPerfume(perfumeId);
            List<AvaliacaoResponse> response = MapearAvaliacaoAvaliacaoResponse(lista);

            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("obter-avaliacoes-cliente")
    public ResponseEntity<List<AvaliacaoResponse>> obterAvaliacoesCliente() {
        try {
            Long clienteId = SecurityUtil.obterClienteId();

            List<Avaliacao> lista = _avaliacaoService.obterAvaliacoesCliente(clienteId != null ? clienteId : 0);
            List<AvaliacaoResponse> response = MapearAvaliacaoAvaliacaoResponse(lista);

            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    private List<AvaliacaoResponse> MapearAvaliacaoAvaliacaoResponse(List<Avaliacao> lista) {
        return lista.stream().map((item) -> new AvaliacaoResponse(
                item.getId(),
                item.getPerfume().getId(),
                item.getPerfume().getNome(),
                item.getCliente().getNome(),
                item.getDescricao(),
                item.getAvaliacao()
                )
        ).toList();
    }

    public record AvaliacaoResponse(long id, long perfumeId, String perfume, String cliente, String descricao, int avaliacao) { }

    @DeleteMapping("excluir-avaliacao/{id}")
    public ResponseEntity<Avaliacao> excluirAvaliacao(@PathVariable("id") long id) {
        try {
            Long clienteId = SecurityUtil.obterClienteId();

            _avaliacaoService.excluirAvaliacao(id, clienteId != null ? clienteId : 0);

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