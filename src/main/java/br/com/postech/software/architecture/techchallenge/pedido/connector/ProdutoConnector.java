package br.com.postech.software.architecture.techchallenge.pedido.connector;

import br.com.postech.software.architecture.techchallenge.pedido.dto.ValidaProdutoRequestDTO;
import br.com.postech.software.architecture.techchallenge.pedido.dto.ValidaProdutoResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class ProdutoConnector {

    private static final String MICROSSERVICO_PRODUTO_URI = "https://tech-challenge.produto.com";
    private static final String VALIDATE_PRODUTO_ENDPOINT = "/v1/produtos/validate";

	public ValidaProdutoResponseDTO validaProdutos(ValidaProdutoRequestDTO validaProdutoRequestDTO) throws Exception {
		
		try {
            String url = MICROSSERVICO_PRODUTO_URI.concat(VALIDATE_PRODUTO_ENDPOINT);

			//RestTemplate restTemplate = new RestTemplate();
            //ResponseEntity<MercadoPagoQrCodeResponseDTO> responseEntity = restTemplate.postForEntity(
			//	    url, dto, ValidaClienteResponseDTO.class);
            //return responseEntity.getBody();

			return new ValidaProdutoResponseDTO();
        } catch (Exception exception) {
            throw new Exception("Erro ao validar Produtos: " + exception.getMessage());
        }
    }
}
