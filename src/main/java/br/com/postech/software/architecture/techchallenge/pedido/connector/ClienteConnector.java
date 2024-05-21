package br.com.postech.software.architecture.techchallenge.pedido.connector;

import br.com.postech.software.architecture.techchallenge.pedido.dto.ClienteDTO;
import br.com.postech.software.architecture.techchallenge.pedido.dto.ValidaClienteResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ClienteConnector {

    private static final String MICROSSERVICO_CLIENTE_URI = "http://localhost:8080";
    private static final String VALIDATE_CLIENTE_ENDPOINT = "/v1/clientes/valida";

	public ValidaClienteResponseDTO validaCliente(ClienteDTO dto) throws Exception {
		
		try {
            String url = MICROSSERVICO_CLIENTE_URI.concat(VALIDATE_CLIENTE_ENDPOINT);

			RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<ValidaClienteResponseDTO> responseEntity = restTemplate.postForEntity(
				    url, dto, ValidaClienteResponseDTO.class);
            return responseEntity.getBody();

        } catch (Exception exception) {
            throw new Exception("Erro ao validar Cliente: " + exception.getMessage());
        }
    }
}
