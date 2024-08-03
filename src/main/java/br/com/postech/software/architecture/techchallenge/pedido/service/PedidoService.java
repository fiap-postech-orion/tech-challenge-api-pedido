package br.com.postech.software.architecture.techchallenge.pedido.service;

import br.com.postech.software.architecture.techchallenge.pedido.dto.PedidoDTO;
import br.com.postech.software.architecture.techchallenge.pedido.exception.BusinessException;
import br.com.postech.software.architecture.techchallenge.pedido.model.Pedido;

import java.util.List;

public interface PedidoService {

	public List<PedidoDTO> findTodosPedidosAtivos() throws BusinessException;

	public Pedido findById(Integer id) throws BusinessException;

	public PedidoDTO getDtoById(Integer id) throws BusinessException;

	public PedidoDTO fazerPedidoFake(PedidoDTO pedidoDTO) throws Exception;

	public PedidoDTO updateStatus(Integer id, String status) throws Exception;

	public PedidoDTO salvarPedido(PedidoDTO pedidoDTO) throws Exception;

	public void salvarQrCode(Integer id, String qrCode);
}
