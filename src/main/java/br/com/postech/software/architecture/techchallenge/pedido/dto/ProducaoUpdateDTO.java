
package br.com.postech.software.architecture.techchallenge.pedido.dto;

import lombok.Data;

@Data
public class ProducaoUpdateDTO {

	private String uuid;
    private PedidoDTO pedidoDTO;

    public ProducaoUpdateDTO(PedidoDTO pedidoDTO){
        this.pedidoDTO = pedidoDTO;
    }
}
