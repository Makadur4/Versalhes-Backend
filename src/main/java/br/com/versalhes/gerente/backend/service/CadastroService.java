package br.com.versalhes.gerente.backend.service;

import br.com.versalhes.gerente.backend.model.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FilenameFilter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Service
public class CadastroService {

    final PerfumeRepository _perfumeRepository;
    final MarcaRepository _marcaRepository;
    final TipoRepository _tipoRepository;

    @Value("${upload.path}")
    private String _pathImagens;

    public CadastroService(PerfumeRepository perfumeRepository, MarcaRepository marcaRepository, TipoRepository tipoRepository) {

        _perfumeRepository = perfumeRepository;
        _marcaRepository = marcaRepository;
        _tipoRepository = tipoRepository;

    }

    public List<Marca> ConsultarMarcaTodas() {

        List<Marca> lista = _marcaRepository.findAll();

        return lista;

    }

    public List<Tipo> ConsultarTipoTodos() {

        List<Tipo> lista = _tipoRepository.findAll();

        return lista;

    }

    @Transactional
    public Perfume IncluirPerfume(Perfume perfume) {

        Perfume novoPerfume = _perfumeRepository.save(perfume);

        return novoPerfume;

    }

    @Transactional
    public Perfume AtualizarPerfume(Perfume perfume) throws Exception {

        Optional<Perfume> perfumeExistente = _perfumeRepository.findById(perfume.getIdPerfume());

        if (perfumeExistente.isPresent()) {

            Perfume perfumeAtualizado = _perfumeRepository.save(perfume);

            return perfumeAtualizado;

        }

        throw new Exception();

    }

    public void ExcluirPerfume(long idPerfume) throws Exception {

        Optional<Perfume> perfumeExistente = _perfumeRepository.findById(idPerfume);

        if (perfumeExistente.isPresent()) {

            _perfumeRepository.deleteById(idPerfume);

            return;

        }

        throw new Exception();

    }

    public List<Perfume> ConsultarPerfumeTodos() {

        List<Perfume> lista = _perfumeRepository.findAll();

        return lista;

    }

    public Perfume ConsultarPerfumePeloId(long idPerfume) throws Exception {

        Optional<Perfume> perfumeExistente = _perfumeRepository.findById(idPerfume);

        if (perfumeExistente.isPresent()) {

            return perfumeExistente.get();

        }

        throw new Exception();

    }

    @Transactional
    public Perfume AtualizarEstoque(long idPerfume, long estoque) throws Exception {

        Optional<Perfume> perfumeExistente = _perfumeRepository.findById(idPerfume);

        if (perfumeExistente.isPresent()) {

            perfumeExistente.get().setEstoque(estoque);

            Perfume perfumeAtualizado = _perfumeRepository.save(perfumeExistente.get());

            return perfumeAtualizado;

        }

        throw new Exception();

    }

    public void GravarImagem(long idPerfume, MultipartFile arquivo) throws Exception {

        Path pathGravacao = Paths.get(_pathImagens);

        if (!Files.exists(pathGravacao)) {

            Files.createDirectories(pathGravacao);

        }

        Path pathCompleto = pathGravacao.resolve(String.format("Perfume%d.png", idPerfume));

        Files.write(pathCompleto, arquivo.getBytes());

    }

    public byte[] ObterImagem(long idPerfume) throws Exception {

        Path pathConsulta = Paths.get(_pathImagens);

        Path pathCompleto = pathConsulta.resolve(String.format("Perfume%d.png", idPerfume));

        return Files.readAllBytes(pathCompleto);

    }

    private String ObterExtensaoArquivo(String nome) throws Exception {

        int lastIndexOfDot = nome.lastIndexOf(".");

        if (lastIndexOfDot == -1) {
            throw new Exception();
        }

        return nome.substring(lastIndexOfDot + 1);
    }

}