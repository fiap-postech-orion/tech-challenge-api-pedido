package br.com.postech.software.architecture.techchallenge.pedido.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import java.text.Normalizer;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDTO {

	private Long id;

	@Size(min = 1, max = 250)
	private String nome;

	@Size(min = 1, max = 250)
	@Email(message = "Email inválido, digite novamente", regexp = ".+[@].+[\\.].+")
	private String email;

	@Size(min = 1, max = 250)
	@CPF(message="CPF inválido, digite novamente")
	private String cpf;

	@Size(min = 6, max = 20)
	@NotNull
	private String senha;

	private Boolean status;

	public ClienteDTO(Long id){
		this.id = id;
	}
}
