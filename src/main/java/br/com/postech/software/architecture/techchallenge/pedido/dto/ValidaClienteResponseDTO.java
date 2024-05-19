package br.com.postech.software.architecture.techchallenge.pedido.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder(toBuilder = true, setterPrefix = "set")
@AllArgsConstructor
@NoArgsConstructor
public class ValidaClienteResponseDTO {

    private boolean isValid;
    private String errorMessage;
    private ClienteDTO clienteDTO;
}
