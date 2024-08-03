package br.com.postech.software.architecture.techchallenge.pedido.producer;

import br.com.postech.software.architecture.techchallenge.pedido.dto.PedidoDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

@Slf4j
@AllArgsConstructor
public class RabbitMQProducer {

    private String exchange;

    private static final String validaClienteRoutingKey = "validaCliente";

    private RabbitTemplate template;

    public void sendToValidaClienteQueue(PedidoDTO pedido) {
        log.info("Message send: [{}]", pedido.toString());
        template.convertAndSend(exchange, validaClienteRoutingKey, pedido);
    }
}