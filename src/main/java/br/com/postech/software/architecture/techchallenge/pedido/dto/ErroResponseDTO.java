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
public class ErroResponseDTO {

    private boolean isValid;
    private String errorMessage;
}
