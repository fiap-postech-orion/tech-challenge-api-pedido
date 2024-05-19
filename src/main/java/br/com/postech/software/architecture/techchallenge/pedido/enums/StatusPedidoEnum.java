package br.com.postech.software.architecture.techchallenge.pedido.enums;

public enum StatusPedidoEnum implements APIEnum{

	DESCONHECIDO(0, "Desconhecido"),
	REALIZADO(1, "Realizado"),
	PENDENTE(2, "Pendente"),
	CONFIRMADO(3, "Confirmado"),
	EM_PREPARACAO(4, "Em preparação"),
	CONCLUIDO(5, "Concluido"),
	CANCELADO(6, "Cancelado");

	private Integer value;
	private String descricao;

	private StatusPedidoEnum(Integer value, String descricao) {
		this.value = value;
		this.descricao = descricao;
	}

	@Override
	public Integer getValue() {
		return value;
	}


	public String getDescricao() {
		return descricao;
	}

	public static StatusPedidoEnum get(Integer value) {
		for (StatusPedidoEnum status : StatusPedidoEnum.values()) {
			if(status.getValue() == value ) {
				return status;
			}
		}
		return StatusPedidoEnum.DESCONHECIDO;
	}

	public static StatusPedidoEnum getByDescricao(String descricao) {
		for (StatusPedidoEnum status : StatusPedidoEnum.values()) {
			if(status.getDescricao().equals(descricao)) {
				return status;
			}
		}
		return StatusPedidoEnum.DESCONHECIDO;
	}
}
