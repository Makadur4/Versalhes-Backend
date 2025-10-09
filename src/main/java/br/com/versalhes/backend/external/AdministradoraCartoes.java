package br.com.versalhes.backend.external;

import br.com.versalhes.backend.dto.DadosPagamentoDto;

public class AdministradoraCartoes {

    public static boolean efetuarPagamento(DadosPagamentoDto dadosPagamento)
    {
        int codigoSeguranca = Integer.parseInt(dadosPagamento.codigoSeguranca());

        //PROCESSO DE PAGAMENTO COM UMA ADMINISTRADORA REAL

        return codigoSeguranca % 2 == 0;
    }
}
