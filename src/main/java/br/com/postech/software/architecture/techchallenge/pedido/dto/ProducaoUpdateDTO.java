
package br.com.postech.software.architecture.techchallenge.pedido.dto;

import br.com.postech.software.architecture.techchallenge.pedido.enums.StatusPedidoEnum;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;

@Data
public class ProducaoUpdateDTO {

	private String uuid;
    private Long numeroPedido;
    private ClienteDTO cliente;
    private String dataPedido;
    private String statusPedido;
    @NotEmpty
    private List<PedidoProdutoDTO> produtos;

    public ProducaoUpdateDTO(PedidoDTO pedidoDTO){
        this.numeroPedido = pedidoDTO.getNumeroPedido();
        this.cliente = pedidoDTO.getCliente();
        this.dataPedido = pedidoDTO.getDataPedido();
        this.statusPedido = StatusPedidoEnum.get(pedidoDTO.getStatusPedido()).getDescricao();
        this.produtos = pedidoDTO.getProdutos();
    }
}
