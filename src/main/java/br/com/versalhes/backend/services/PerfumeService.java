package br.com.versalhes.backend.services;

import br.com.versalhes.backend.models.*;

import br.com.versalhes.backend.repositories.PerfumeRepository;

import jakarta.transaction.Transactional;
import jakarta.persistence.criteria.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.*;

@Service
public class PerfumeService {
    @Autowired
    PerfumeRepository _perfumeRepository;

    @Value("${upload.path}")
    private String _pathImagens;

    @Transactional
    public Perfume incluirPerfume(Perfume perfume) throws Exception {
        return _perfumeRepository.save(perfume);
    }

    public List<Perfume> obterPerfumesCadastro(String nome) {
        return _perfumeRepository.findAll(PerfumeSpecification.filtrar(nome));
    }

    public List<Perfume> obterPerfumesAvaliacao(String nome, List<Long> marcas, List<Long> tipos, Ordenacao ordenacao) {
        List<Perfume> lista = _perfumeRepository.findAll(PerfumeSpecification.filtrar(nome, marcas, tipos));

        switch (ordenacao) {
            case MAIS_RELEVANTES -> lista.sort(Comparator.comparing(Perfume::getMediaAvaliacao).reversed());
            case MAIS_AVALIADOS -> lista.sort(Comparator.comparing(Perfume::getTotalAvaliacoes).reversed());
            case MAIS_VENDIDOS -> lista.sort(Comparator.comparing(Perfume::getTotalPedidos).reversed());
            case MENOS_VENDIDOS -> lista.sort(Comparator.comparing(Perfume::getTotalPedidos));
        }

        return  lista;
    }

    public List<Perfume> obterPerfumesVenda(String nome, Secao secao, Double preco, List<Long> marcas, List<Long> tipos) {
        return _perfumeRepository.findAll(PerfumeSpecification.filtrar(nome, marcas, tipos));
    }

    public static class PerfumeSpecification {

        public static Specification<Perfume> filtrar(String nome) {
            return filtrar(nome, Secao.NENHUMA, 0.0, null, null);
        }

        public static Specification<Perfume> filtrar(String nome, List<Long> marcas, List<Long> tipos) {
            return filtrar(nome, Secao.NENHUMA, 0.0, marcas, tipos);
        }

        public static Specification<Perfume> filtrar(String nome, Secao secao, Double preco, List<Long> marcas, List<Long> tipos) {
            return (root, query, cb) -> {
                List<Predicate> predicates = new ArrayList<>();

                if (nome != null && !nome.isEmpty()) {
                    predicates.add(cb.like(cb.lower(root.get("nome")), "%" + nome.toLowerCase() + "%"));
                }

                if (secao == Secao.MASCULINOS) {
                    predicates.add(cb.equal(root.get("genero"), "M"));
                }

                if (secao == Secao.FEMININOS) {
                    predicates.add(cb.equal(root.get("genero"), "F"));
                }

                if (secao == Secao.ESPECIAIS) {
                    predicates.add(cb.equal(root.get("especial"), true));
                }

                if (secao == Secao.OFERTAS) {
                    predicates.add(cb.equal(root.get("oferta"), true));
                }

                if (preco != 0) {
                    predicates.add(cb.gt(root.get("precoVenda"), preco));
                }

                if (marcas != null && !marcas.isEmpty()) {
                    predicates.add(root.get("marca").in(marcas));
                }

                if (tipos != null && !tipos.isEmpty()) {
                    predicates.add(root.get("tipo").in(tipos));
                }

                return cb.and(predicates.toArray(new Predicate[0]));
            };
        }
    }

    public enum Ordenacao {
        NENHUMA(""),
        MAIS_RELEVANTES("MaisRelevantes"),
        MAIS_AVALIADOS("MaisAvaliados"),
        MAIS_VENDIDOS("MaisVendidos"),
        MENOS_VENDIDOS("MenosVendidos");

        private final String _nome;

        Ordenacao(String nome) {
            _nome = nome;
        }

        public String getNome() {return _nome; }

        public static Ordenacao obterEnum(String valor)
        {
            return Arrays.stream(values()).filter(item -> item.getNome().equalsIgnoreCase(valor)).findFirst().orElse(NENHUMA);
        }
    }

    public enum Secao {
        NENHUMA(""),
        MASCULINOS("Masculinos"),
        FEMININOS("Femininos"),
        ESPECIAIS("Especiais"),
        OFERTAS("Ofertas");

        private final String _nome;

        Secao(String nome) {
            _nome = nome;
        }

        public String getNome() {return _nome;}

        public static Secao obterEnum(String valor)
        {
            return Arrays.stream(values()).filter(item -> item.getNome().equalsIgnoreCase(valor)).findFirst().orElse(NENHUMA);
        }
    }

    public Perfume obterPerfume(long id) throws Exception {
        return _perfumeRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Transactional
    public Perfume alterarPerfume(Perfume perfume) throws Exception {
        _perfumeRepository.findById(perfume.getId()).orElseThrow();

        return _perfumeRepository.save(perfume);
    }

    @Transactional
    public void alterarEstoque(long id, long estoque) throws Exception {
        Perfume perfumeExistente = _perfumeRepository.findById(id).orElseThrow();

        perfumeExistente.setEstoque(estoque);

        _perfumeRepository.save(perfumeExistente);
    }

    @Transactional
    public void excluirPerfume(long id) throws Exception {
        Perfume perfumeExistente = _perfumeRepository.findById(id).orElseThrow();

        _perfumeRepository.deleteById(perfumeExistente.getId());
    }

    public void incluirImagem(long id, MultipartFile arquivo) throws Exception {
        _perfumeRepository.findById(id).orElseThrow();

        Path pathGravacao = Paths.get(_pathImagens);

        if (!Files.exists(pathGravacao)) {
            Files.createDirectories(pathGravacao);
        }

        Path pathCompleto = pathGravacao.resolve(String.format("Perfume%d.png", id));

        Files.write(pathCompleto, arquivo.getBytes());
    }

    public byte[] obterImagem(long id) throws FileNotFoundException, Exception {
        Path pathConsulta = Paths.get(_pathImagens);
        Path pathCompleto = pathConsulta.resolve(String.format("Perfume%d.png", id));

        if(!Files.exists(pathCompleto)) {
            throw new FileNotFoundException();
        }

        return Files.readAllBytes(pathCompleto);
    }
}