package br.com.postech.software.architecture.techchallenge.pedido.consumer;

import br.com.postech.software.architecture.techchallenge.pedido.connector.PagamentoConnector;
import br.com.postech.software.architecture.techchallenge.pedido.dto.ErroResponseDTO;
import br.com.postech.software.architecture.techchallenge.pedido.dto.PedidoDTO;
import br.com.postech.software.architecture.techchallenge.pedido.enums.StatusPedidoEnum;
import br.com.postech.software.architecture.techchallenge.pedido.service.PedidoService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class ErroValidacaoConsumer {

    @Autowired
    private PedidoService pedidoService;

    @RabbitListener (queues = {"erroValidacao"})
    public void consume(ErroResponseDTO erroValidacaoDTO) throws Exception {
        log.info("erro {}", erroValidacaoDTO.toString());
        pedidoService.updateStatus(erroValidacaoDTO.getPedidoId(), StatusPedidoEnum.CANCELADO.getDescricao());
    }
}
