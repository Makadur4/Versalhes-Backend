package br.com.versalhes.backend.controllers;

import br.com.versalhes.backend.models.Perfume;

import br.com.versalhes.backend.security.SecurityUtil;
import br.com.versalhes.backend.services.PerfumeService;

import org.apache.tomcat.util.http.fileupload.FileUploadException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;

import java.util.*;

@RestController
@CrossOrigin(origins="*", maxAge = 3600, allowCredentials = "false")
@RequestMapping("/perfume")
public class PerfumeController {
    @Autowired
    PerfumeService _perfumeService;

    @PostMapping("incluir-perfume")
    public ResponseEntity<Perfume> incluirPerfume(@RequestBody Perfume perfume) {
        try {
            if(SecurityUtil.obterUsuarioId() == null)
            {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }

            Perfume novoPerfume = _perfumeService.incluirPerfume(perfume);

            return ResponseEntity.status(HttpStatus.CREATED).body(novoPerfume);
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("obter-perfumes-cadastro")
    public ResponseEntity<List<Perfume>> obterPerfumesCadastro(@RequestParam(required = false) String nome) {
        try {
            if(SecurityUtil.obterUsuarioId() == null)
            {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }

            List<Perfume> lista = _perfumeService.obterPerfumesCadastro(nome);

            return ResponseEntity.status(HttpStatus.OK).body(lista);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("obter-perfumes-avaliacao")
    public ResponseEntity<List<Perfume>> obterPerfumesAvaliacao(@RequestParam String nome, @RequestParam String marcas, @RequestParam String tipos, @RequestParam String ordenacao) {
        try {
            List<Long> filtroMarcas = marcas.isEmpty() ? new ArrayList<Long>() : Arrays.asList(marcas.split("\\|")).stream().map(Long::parseLong).toList();
            List<Long> filtroTipos = tipos.isEmpty() ? new ArrayList<Long>() : Arrays.asList(tipos.split("\\|")).stream().map(Long::parseLong).toList();
            PerfumeService.Ordenacao opcaoOrdenacao = PerfumeService.Ordenacao.obterEnum(ordenacao);

            List<Perfume> lista = _perfumeService.obterPerfumesAvaliacao(nome, filtroMarcas, filtroTipos, opcaoOrdenacao);

            return ResponseEntity.status(HttpStatus.OK).body(lista);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("obter-perfumes-venda")
    public ResponseEntity<List<Perfume>> obterPerfumesVenda(@RequestParam String nome, @RequestParam String secao, @RequestParam String preco, @RequestParam String marcas, @RequestParam String tipos) {
        try {
            PerfumeService.Secao filtroSecao = PerfumeService.Secao.obterEnum(secao);
            Double filtroPreco = preco != null ? Double.parseDouble(preco) : 0;
            List<Long> filtroMarcas = Arrays.asList(marcas.split("\\|")).stream().map(Long::parseLong).toList();
            List<Long> filtroTipos = Arrays.asList(tipos.split("\\|")).stream().map(Long::parseLong).toList();

            List<Perfume> lista = _perfumeService.obterPerfumesVenda(nome, filtroSecao, filtroPreco, filtroMarcas, filtroTipos);

            return ResponseEntity.status(HttpStatus.OK).body(lista);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("obter-perfume/{id}")
    public ResponseEntity<Perfume> obterPerfume(@PathVariable("id") long id) {
        try {
            Perfume perfumeExistente = _perfumeService.obterPerfume(id);

            return ResponseEntity.status(HttpStatus.OK).body(perfumeExistente);
        } catch(NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("alterar-perfume/{id}")
    public ResponseEntity<Perfume> alterarPerfume(@PathVariable("id") long id, @RequestBody Perfume perfume) {
        try {
            if(SecurityUtil.obterUsuarioId() == null)
            {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }

            perfume.setId(id);

            Perfume perfumeAtualizado = _perfumeService.alterarPerfume(perfume);

            return ResponseEntity.status(HttpStatus.OK).body(perfumeAtualizado);
        } catch(NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PatchMapping("alterar-estoque/{id}")
    public ResponseEntity<Object> alterarEstoque(@PathVariable("id") long id, @RequestBody AlterarEstoqueRequest request) {
        try {
            _perfumeService.alterarEstoque(id, request.estoque);

            return ResponseEntity.status(HttpStatus.OK).build();
        } catch(NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    public record AlterarEstoqueRequest(long estoque) { };

    @DeleteMapping("excluir-perfume/{id}")
    public ResponseEntity<Void> excluirPerfume(@PathVariable("id") long id) {
        try {
            _perfumeService.excluirPerfume(id);

            return ResponseEntity.status(HttpStatus.OK).build();
        } catch(NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("incluir-imagem")
    public ResponseEntity<Void> incluirImagem(@RequestParam("perfumeId") String perfumeId, @RequestParam("arquivo") MultipartFile arquivo) {
        try {
            if(arquivo == null || arquivo.isEmpty()) { throw new FileUploadException(""); }

            _perfumeService.incluirImagem(Long.parseLong(perfumeId), arquivo);

            return ResponseEntity.status(HttpStatus.OK).build();
        } catch(NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (FileUploadException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("obter-imagem/{id}")
    public ResponseEntity<byte[]> obterImagem(@PathVariable("id") long id) {
        try {
            byte[] imagem = _perfumeService.obterImagem(id);

            return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"Perfume" + id + ".jpg\"").body(imagem);
        } catch(FileNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
