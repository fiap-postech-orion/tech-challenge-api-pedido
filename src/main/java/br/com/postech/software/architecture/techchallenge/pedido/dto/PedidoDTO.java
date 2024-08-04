package br.com.postech.software.architecture.techchallenge.pedido.dto;

import br.com.postech.software.architecture.techchallenge.pedido.model.Pedido;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidoDTO {

	private Long numeroPedido;
    private ClienteDTO cliente;
    private String dataPedido;
    private Integer statusPedido;
    private String qrCode;
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
