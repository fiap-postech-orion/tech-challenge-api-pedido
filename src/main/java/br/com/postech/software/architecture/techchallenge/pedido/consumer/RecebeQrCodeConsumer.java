package br.com.postech.software.architecture.techchallenge.pedido.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;

public class RecebeQrCodeConsumer {

    @RabbitListener (queues = {"${valida.produtos.callback.queue}"})
    public void consume(String message) {

    }
}
