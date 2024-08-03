package br.com.postech.software.architecture.techchallenge.pedido.configuration;

import com.rabbitmq.client.AMQP;
import org.apache.camel.language.simple.Simple;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.amqp.rabbit.support.MessagePropertiesConverter;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;

@Configuration
public class RabbitMQConfig {

//	@Bean
//	public Queue queue(){
//		return new Queue("testQueue");
//	}
//
//	@Bean
//	public TopicExchange exchange(){
//		return new TopicExchange("testExchange");
//	}
//
//	@Bean
//	public Binding binding(){
//		return BindingBuilder
//				.bind(queue())
//				.to(exchange())
//				.with("testRoutingKey");
//	}

	@Bean
	public ConnectionFactory getRabbitConnectionFactory(){
		CachingConnectionFactory factory = new CachingConnectionFactory("host", 1);//host e port
		factory.setVirtualHost("/");
		factory.setUsername("");
		factory.setPassword("");
		factory.setRequestedHeartBeat(10);
		return factory;
	}

	public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(){
		SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
		factory.setConnectionFactory(this.getRabbitConnectionFactory());
		factory.setAcknowledgeMode(AcknowledgeMode.AUTO);
		return factory;
	}

	@Bean
	public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory) {
		RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setMessageConverter(producerConverter());
		return rabbitTemplate;
	}
	@Bean
	public MessageConverter producerConverter() {
		return new Jackson2JsonMessageConverter();
	}
}