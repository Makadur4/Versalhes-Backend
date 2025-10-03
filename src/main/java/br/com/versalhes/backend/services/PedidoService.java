package br.com.versalhes.backend.services;

import br.com.versalhes.backend.models.*;

import br.com.versalhes.backend.repositories.*;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PedidoService {
    @Autowired
    ClienteRepository _clienteRepository;

    @Autowired
    FreteRepository _freteRepository;

    @Autowired
    CondicaoPagamentoRepository _condicaoPagamentoRepository;

    @Autowired
    PerfumeRepository _perfumeRepository;

    @Autowired
    PedidoRepository _pedidoRepository;

    @Autowired
    EnderecoEntregaRepository _enderecoEntregaRepository;

    @Autowired
    DadosPagamentoRepository _dadosPagamentoRepository;

    @Autowired
    ItemPedidoRepository _itemPedidoRepository;

    @Transactional
    public Pedido incluirPedido(long clienteId, long freteId, long condicaoPagamentoId, DadosPagamento dadosPagamento, List<ItemPedido> itensPedido) {
        Cliente clienteExistente = _clienteRepository.findById(clienteId).orElseThrow();
        Frete freteExistente = _freteRepository.findById(freteId).orElseThrow();;
        CondicaoPagamento condicaoPagamentoExistente = _condicaoPagamentoRepository.findById(condicaoPagamentoId).orElseThrow();;
        EnderecoCliente enderecoCliente = clienteExistente.getEnderecoCliente();

        for(ItemPedido itemPedido : itensPedido)
        {
            Perfume perfumeExistente = _perfumeRepository.findById(itemPedido.getPerfume().getId()).orElseThrow();

            if(perfumeExistente.getEstoque() < itemPedido.getQuantidade()) { throw new DataIntegrityViolationException("Quantidade disponível inferior ao solicitado"); }

            perfumeExistente.setEstoque(perfumeExistente.getEstoque() - itemPedido.getQuantidade());

            itemPedido.setPerfume(perfumeExistente);
            itemPedido.setValorUnitario(perfumeExistente.getPrecoVenda());
        }

        LocalDate dataPedido = LocalDate.now();

        double valorProdutos = itensPedido.stream().mapToDouble(item -> item.getValorUnitario() * item.getQuantidade()).sum();
        double subTotal = valorProdutos + freteExistente.getValor();
        double valorAcrescimos = subTotal * condicaoPagamentoExistente.getPercentualAcrescimo() / 100.0;
        double valorTotal = subTotal + valorAcrescimos;

        Pedido pedido = new Pedido();
        pedido.setCliente(clienteExistente);
        pedido.setDataPedido(dataPedido);
        pedido.setValorProdutos(valorProdutos);
        pedido.setPrazoFrete(freteExistente.getPrazo());
        pedido.setValorFrete(freteExistente.getValor());
        pedido.setDataEntrega(dataPedido.plusDays(freteExistente.getPrazo()));
        pedido.setValorAcrescimo(valorAcrescimos);
        pedido.setValorTotal(valorTotal);
        pedido.setStatus(Status.EFETIVADO);

        EnderecoEntrega enderecoEntrega = new EnderecoEntrega();
        enderecoEntrega.setCep(enderecoCliente.getCep());
        enderecoEntrega.setEndereco(enderecoCliente.getEndereco());
        enderecoEntrega.setNumero(enderecoCliente.getNumero());
        enderecoEntrega.setComplemento(enderecoCliente.getComplemento());
        enderecoEntrega.setBairro(enderecoCliente.getBairro());
        enderecoEntrega.setMunicipio(enderecoCliente.getMunicipio());
        enderecoEntrega.setUf(enderecoCliente.getUf());

        Pedido novoPedido = _pedidoRepository.save(pedido);

        enderecoEntrega.setPedidoId(novoPedido.getId());
        EnderecoEntrega novoEnderecoEntrega = _enderecoEntregaRepository.save(enderecoEntrega);

        novoPedido.setEnderecoEntrega(novoEnderecoEntrega);

        String numeroCartao = dadosPagamento.getNumeroCartao().split("-")[3];

        dadosPagamento.setNumeroCartao(numeroCartao);
        dadosPagamento.setQuantidadeParcelas(condicaoPagamentoExistente.getQuantidadeParcelas());
        dadosPagamento.setPedidoId(novoPedido.getId());
        DadosPagamento novosDadosPagamento =  _dadosPagamentoRepository.save(dadosPagamento);

        novoPedido.setDadosPagamento(novosDadosPagamento);

        for(ItemPedido itemPedido : itensPedido)
        {
            itemPedido.setPedido(novoPedido);
            ItemPedido novoItemPedido = _itemPedidoRepository.save(itemPedido);

            _perfumeRepository.save(itemPedido.getPerfume());
        }

        novoPedido.setItensPedido(itensPedido);

        return novoPedido;
    }

    public List<Pedido> obterPedidos(long clienteId) throws Exception {
        List<Pedido> lista = _pedidoRepository.findByClienteId(clienteId);

        lista.sort(Comparator.comparing(Pedido::getDataPedido).reversed());

        return lista;
    }

    public Pedido obterPedido(long id, long clienteId) throws Exception {
        Pedido pedidoExistente = _pedidoRepository.findById(id).orElseThrow(NoSuchElementException::new);

        if(pedidoExistente.getCliente().getId() != clienteId) { throw new NoSuchElementException(); }

        return pedidoExistente;
    }

    @Transactional
    public void cancelarPedido(Long id, long clienteId) throws Exception {
        Pedido pedidoExistente = _pedidoRepository.findById(id).orElseThrow();

        if(pedidoExistente.getCliente().getId() != clienteId) { throw new NoSuchElementException(); }

        pedidoExistente.setStatus(Status.CANCELADO);

        _pedidoRepository.save(pedidoExistente);
    }
}
