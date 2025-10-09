package br.com.versalhes.backend.dto;

public record DadosPagamentoDto(String bandeiraCartao, String numeroCartao, String nomeCartao, String dataValidade, String codigoSeguranca){}

