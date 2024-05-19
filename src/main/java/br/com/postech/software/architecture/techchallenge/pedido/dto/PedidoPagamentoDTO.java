package br.com.postech.software.architecture.techchallenge.pedido.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PedidoPagamentoDTO {

    private String qrCode;
    private PedidoDTO pedidoDTO;
}
