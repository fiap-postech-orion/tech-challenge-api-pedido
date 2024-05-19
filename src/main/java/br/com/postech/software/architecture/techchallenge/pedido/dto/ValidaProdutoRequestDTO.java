package br.com.postech.software.architecture.techchallenge.pedido.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ValidaProdutoRequestDTO {

    private List<ProdutoDTO> produtoDTOs;
}
