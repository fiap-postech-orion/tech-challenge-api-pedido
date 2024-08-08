package br.com.postech.software.architecture.techchallenge.pedido.consumer;

import br.com.postech.software.architecture.techchallenge.pedido.connector.PagamentoConnector;
import br.com.postech.software.architecture.techchallenge.pedido.dto.ErroResponseDTO;
import br.com.postech.software.architecture.techchallenge.pedido.dto.PedidoDTO;
import br.com.postech.software.architecture.techchallenge.pedido.enums.StatusPedidoEnum;
import br.com.postech.software.architecture.techchallenge.pedido.service.PedidoService;
import com.rabbitmq.client.Channel;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
@AllArgsConstructor
public class ErroValidacaoConsumer {

    @Autowired
    private PedidoService pedidoService;

    @RabbitListener (queues = {"erroValidacao"})
    public void consume(ErroResponseDTO erroValidacaoDTO, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws IOException {
        try {
            log.info("erro retornado {}", erroValidacaoDTO.toString());
            pedidoService.updateStatusEDescricao(erroValidacaoDTO.getPedidoId(),
                    StatusPedidoEnum.CANCELADO.getDescricao(),
                    erroValidacaoDTO.getErrorMessage());
        }
        catch(Exception e) {
            log.error("ERRO NO RECEBIMENTO DE FILA [erroValidacao]");
            channel.basicReject(tag, false);
        }
    }
}
