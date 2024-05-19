package br.com.postech.software.architecture.techchallenge.pedido.service;

import br.com.postech.software.architecture.techchallenge.pedido.dto.PedidoDTO;
import br.com.postech.software.architecture.techchallenge.pedido.exception.BusinessException;
import br.com.postech.software.architecture.techchallenge.pedido.model.Pedido;

import java.util.List;

public interface PedidoService {

	List<PedidoDTO> findTodosPedidosAtivos() throws BusinessException;

	Pedido findById(Integer id) throws BusinessException;

	PedidoDTO getDtoById(Integer id) throws BusinessException;

	PedidoDTO fazerPedidoFake(PedidoDTO pedidoDTO) throws Exception;

	PedidoDTO updateStatus(Integer id, String status) throws Exception;
}
