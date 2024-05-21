package br.com.postech.software.architecture.techchallenge.pedido.connector;

import br.com.postech.software.architecture.techchallenge.pedido.dto.PedidoDTO;
import br.com.postech.software.architecture.techchallenge.pedido.dto.ProducaoUpdateDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ProducaoConnector {

    private static final String MICROSSERVICO_PRODUCAO_URI = "http://localhost:8084";
    private static final String REGISTRA_PRODUCAO_ENDPOINT = "/v1/producao";

	public void salvarPedidoBaseLeitura(PedidoDTO pedidoDTO) throws Exception {
		
		try {
            String url = MICROSSERVICO_PRODUCAO_URI.concat(REGISTRA_PRODUCAO_ENDPOINT);
			RestTemplate restTemplate = new RestTemplate();
            restTemplate.postForEntity(
				    url, new ProducaoUpdateDTO(pedidoDTO), ProducaoUpdateDTO.class);

        } catch (Exception exception) {
            throw new Exception("Erro ao registrar Pedido na base de leitura: " + exception.getMessage());
        }
    }

}
