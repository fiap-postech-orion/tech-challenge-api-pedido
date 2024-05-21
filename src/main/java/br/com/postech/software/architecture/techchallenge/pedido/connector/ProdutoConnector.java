package br.com.postech.software.architecture.techchallenge.pedido.connector;

import br.com.postech.software.architecture.techchallenge.pedido.dto.ValidaProdutoRequestDTO;
import br.com.postech.software.architecture.techchallenge.pedido.dto.ValidaProdutoResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ProdutoConnector {

    private static final String MICROSSERVICO_PRODUTO_URI = "http://localhost:8083";
    private static final String VALIDATE_PRODUTO_ENDPOINT = "/v1/produtos/validate";

	public ValidaProdutoResponseDTO validaProdutos(ValidaProdutoRequestDTO validaProdutoRequestDTO) throws Exception {
		
		try {
            String url = MICROSSERVICO_PRODUTO_URI.concat(VALIDATE_PRODUTO_ENDPOINT);

			RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<ValidaProdutoResponseDTO> responseEntity = restTemplate.postForEntity(
				    url, validaProdutoRequestDTO, ValidaProdutoResponseDTO.class);
            return responseEntity.getBody();
        } catch (Exception exception) {
            throw new Exception("Erro ao validar Produtos: " + exception.getMessage());
        }
    }
}
