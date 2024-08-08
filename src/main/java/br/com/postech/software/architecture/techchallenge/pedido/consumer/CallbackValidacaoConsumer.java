package br.com.postech.software.architecture.techchallenge.pedido.consumer;

import br.com.postech.software.architecture.techchallenge.pedido.connector.PagamentoConnector;
import br.com.postech.software.architecture.techchallenge.pedido.dto.PedidoDTO;
import br.com.postech.software.architecture.techchallenge.pedido.model.Pedido;
import br.com.postech.software.architecture.techchallenge.pedido.service.PedidoService;
import com.rabbitmq.client.Channel;
import lombok.AllArgsConstructor;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@AllArgsConstructor
public class CallbackValidacaoConsumer {

    @Autowired
    private PedidoService pedidoService;
    @Autowired
    private PagamentoConnector pagamentoConnector;
    @RabbitListener (queues = {"callbackValidacao"}, ackMode = "MANUAL")
    public void consume(PedidoDTO pedidoDTO, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws IOException {
        try {
            pedidoDTO = pedidoService.salvarPedido(pedidoDTO);
            String qrCode = pagamentoConnector.generateMercadoPagoQrCode(pedidoDTO);
            pedidoService.salvarQrCode(pedidoDTO.getNumeroPedido().intValue(), qrCode);
            channel.basicAck(tag, false);
        }
        catch(Exception e) {
            channel.basicReject(tag, false);
        }
    }
}
