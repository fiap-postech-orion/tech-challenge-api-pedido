package br.com.postech.software.architecture.techchallenge.pedido.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@Builder(toBuilder = true, setterPrefix = "set")
@AllArgsConstructor
@NoArgsConstructor
public class ValidaProdutoResponseDTO {

    private boolean isValid;
    private String errorMessage;
    private List<ProdutoDTO> produtoDTOs;
}
