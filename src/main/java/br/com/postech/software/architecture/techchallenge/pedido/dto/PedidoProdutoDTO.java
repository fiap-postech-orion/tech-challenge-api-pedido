package br.com.postech.software.architecture.techchallenge.pedido.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PedidoProdutoDTO {

	private ProdutoDTO produto;
    @NotNull(message = "A quantidade é obrigatório.")
    private Integer quantidade;
}
