package br.com.versalhes.gerente.backend.control;

import br.com.versalhes.gerente.backend.service.CadastroService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600, allowCredentials = "false")
@RequestMapping("/imagem")
public class ImagemController {
    CadastroService _cadastroService;

    public ImagemController(CadastroService cadastroService) {
        _cadastroService = cadastroService;
    }

    @PostMapping("/{idPerfume}")
    public ResponseEntity<Void> GravarImagem(@PathVariable("idPerfume") long idPerfume, @RequestParam("arquivo") MultipartFile arquivo) {
        try {
            _cadastroService.GravarImagem(idPerfume, arquivo);

            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/{idPerfume}")
    public ResponseEntity<byte[]> ObterImagem(@PathVariable("idPerfume") long idPerfume) {
        try {
            byte[] imagem = _cadastroService.ObterImagem(idPerfume);

            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_PNG)
                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"Perfume" + idPerfume + ".jpg\"")
                    .body(imagem);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
