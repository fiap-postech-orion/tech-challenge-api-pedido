package br.com.postech.software.architecture.techchallenge.pedido.connector;

import br.com.postech.software.architecture.techchallenge.pedido.dto.PedidoDTO;
import org.springframework.stereotype.Component;

@Component
public class ProducaoConnector {

    private static final String MICROSSERVICO_PRODUCAO_URI = "https://tech-challenge.producao.com";
    private static final String REGISTRA_PRODUCAO_ENDPOINT = "/v1/producao";

	public void salvarPedidoBaseLeitura(PedidoDTO pedidoDTO) throws Exception {
		
		try {
            String url = MICROSSERVICO_PRODUCAO_URI.concat(REGISTRA_PRODUCAO_ENDPOINT);
			//RestTemplate restTemplate = new RestTemplate();
            //ResponseEntity<ProducaoUpdateDTO> responseEntity = restTemplate.postForEntity(
			//	    url, new ProducaoUpdateDTO(pedidoDTO), ProducaoUpdateDTO.class);

        } catch (Exception exception) {
            throw new Exception("Erro ao registrar Pedido na base de leitura: " + exception.getMessage());
        }
    }
}
