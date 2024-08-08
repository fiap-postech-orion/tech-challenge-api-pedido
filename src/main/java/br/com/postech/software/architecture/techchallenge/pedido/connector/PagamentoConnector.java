package br.com.postech.software.architecture.techchallenge.pedido.connector;

import br.com.postech.software.architecture.techchallenge.pedido.dto.PedidoDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class PagamentoConnector {

    private static final String MICROSSERVICO_PRODUTO_URI = "http://localhost:8082";

	public String generateMercadoPagoQrCode(PedidoDTO pedidoDTO) throws Exception {
		
		try {
            return "qrCode";
//            String url = MICROSSERVICO_PRODUTO_URI.concat("/v1/pagamento/")
//                    .concat(pedidoDTO.getNumeroPedido().toString())
//                    .concat("/qrCode");
//			RestTemplate restTemplate = new RestTemplate();
//            ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
//            return responseEntity.getBody();

        } catch (Exception exception) {
            throw new Exception("Erro ao gerar QR Code: " + exception.getMessage());
        }
    }
}
