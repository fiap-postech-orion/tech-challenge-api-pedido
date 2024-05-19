package br.com.postech.software.architecture.techchallenge.pedido.configuration;

import br.com.postech.software.architecture.techchallenge.pedido.enums.StatusPedidoEnum;
import org.modelmapper.AbstractConverter;

public class InteiroParaStatusPedidoConverter extends AbstractConverter<Integer, StatusPedidoEnum> {

	@Override
	protected StatusPedidoEnum convert(Integer source) {
		return StatusPedidoEnum.get(source);
	}

}
