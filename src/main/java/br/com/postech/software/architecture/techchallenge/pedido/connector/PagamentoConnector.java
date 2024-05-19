package br.com.postech.software.architecture.techchallenge.pedido.connector;

import br.com.postech.software.architecture.techchallenge.pedido.dto.PedidoDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class PagamentoConnector {

    private static final String MICROSSERVICO_PRODUTO_URI = "https://tech-challenge.pagamento.com";

	public String generateMercadoPagoQrCode(PedidoDTO pedidoDTO) throws Exception {
		
		try {
            String url = MICROSSERVICO_PRODUTO_URI.concat("/v1/pagamento/qrCode");
			RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, pedidoDTO, String.class);
            return responseEntity.getBody();

        } catch (Exception exception) {
            throw new Exception("Erro ao gerar QR Code: " + exception.getMessage());
        }
    }
}
