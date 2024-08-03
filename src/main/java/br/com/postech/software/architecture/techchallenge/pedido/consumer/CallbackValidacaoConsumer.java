package br.com.postech.software.architecture.techchallenge.pedido.consumer;

import br.com.postech.software.architecture.techchallenge.pedido.connector.PagamentoConnector;
import br.com.postech.software.architecture.techchallenge.pedido.dto.PedidoDTO;
import br.com.postech.software.architecture.techchallenge.pedido.model.Pedido;
import br.com.postech.software.architecture.techchallenge.pedido.service.PedidoService;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CallbackValidacaoConsumer {

    @Autowired
    private PedidoService pedidoService;
    @Autowired
    private PagamentoConnector pagamentoConnector;
    @RabbitListener (queues = {"${callback.validacao.queue}"})
    public void consume(PedidoDTO pedidoDTO) throws Exception {
        pedidoDTO = pedidoService.salvarPedido(pedidoDTO);
        String qrCode = pagamentoConnector.generateMercadoPagoQrCode(pedidoDTO);
        pedidoService.salvarQrCode(pedidoDTO.getNumeroPedido().intValue(), qrCode);
    }
}
