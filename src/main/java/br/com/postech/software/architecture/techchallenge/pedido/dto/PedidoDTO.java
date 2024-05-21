package br.com.postech.software.architecture.techchallenge.pedido.dto;

import br.com.postech.software.architecture.techchallenge.pedido.model.Pedido;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class PedidoDTO {

	private Long numeroPedido;
    private ClienteDTO cliente;
    private String dataPedido;
    private Integer statusPedido;
    @NotEmpty
    private List<PedidoProdutoDTO> produtos;

    public PedidoDTO(Pedido pedido) {
        this.numeroPedido= pedido.getId();
        this.cliente= new ClienteDTO(pedido.getClienteId());
        this.dataPedido=pedido.getDataPedido().toString();
        this.statusPedido=pedido.getStatusPedidoId();
    }

    public void updateNumeroPedido(Long idPedido){
        this.numeroPedido = idPedido;
    }
}
