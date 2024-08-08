package br.com.postech.software.architecture.techchallenge.pedido.service.impl;

import br.com.postech.software.architecture.techchallenge.pedido.configuration.ModelMapperConfiguration;
import br.com.postech.software.architecture.techchallenge.pedido.connector.ClienteConnector;
import br.com.postech.software.architecture.techchallenge.pedido.connector.ProducaoConnector;
import br.com.postech.software.architecture.techchallenge.pedido.connector.ProdutoConnector;
import br.com.postech.software.architecture.techchallenge.pedido.dto.*;
import br.com.postech.software.architecture.techchallenge.pedido.enums.StatusPedidoEnum;
import br.com.postech.software.architecture.techchallenge.pedido.exception.BusinessException;
import br.com.postech.software.architecture.techchallenge.pedido.exception.NotFoundException;
import br.com.postech.software.architecture.techchallenge.pedido.model.Pedido;
import br.com.postech.software.architecture.techchallenge.pedido.repository.jpa.PedidoJpaRepository;
import br.com.postech.software.architecture.techchallenge.pedido.service.PedidoService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class PedidoServiceImpl implements PedidoService {
	private static final ModelMapper MAPPER = ModelMapperConfiguration.getModelMapper();

	private final PedidoJpaRepository pedidoJpaRepository;
	private ClienteConnector clienteConnector;
	private ProdutoConnector produtoConnector;
	private ProducaoConnector producaoConnector;

    protected PedidoJpaRepository getPersistencia() {
		return pedidoJpaRepository;
	}

	private Pedido save(Pedido pedido) {
		return getPersistencia().save(pedido);
    }

	@Override
	public List<PedidoDTO> findTodosPedidosAtivos()throws BusinessException {

		List<Pedido> pedidos = getPersistencia().findByStatusPedidoIdNotIn(
				Arrays.asList(5,6),
				Sort.by(Sort.Direction.ASC, "dataPedido")
		);
		return pedidos.stream().map(pedido -> new PedidoDTO(pedido)).collect(Collectors.toList());
	}

	public Pedido findById(Integer id) throws BusinessException {
		return getPersistencia()
				.findById(id)
				.orElseThrow(() -> new NotFoundException("Pedido não encontrado!"));
	}

	@Override
	public PedidoDTO getDtoById(Integer id) throws BusinessException{
		Pedido pedido = getPersistencia()
				.findById(id)
				.orElseThrow(() -> new NotFoundException("Pedido não encontrado!"));

		return MAPPER.map(pedido, PedidoDTO.class);
	}

	@Override
	public PedidoDTO updateStatus(Integer id, String status) throws Exception {
		Pedido pedido = findById(id);
		pedido.updateStatus(status);
		PedidoDTO pedidoDTO = MAPPER.map(save(pedido), PedidoDTO.class);
		producaoConnector.salvarPedidoBaseLeitura(pedidoDTO);
		return pedidoDTO;
	}

	@Override
	public PedidoDTO updateStatusEDescricao(Integer id, String status, String descricaoErro) throws Exception {
		Pedido pedido = findById(id);
		pedido.updateStatusEDescricao(status, descricaoErro);

		PedidoDTO pedidoDTO = MAPPER.map(save(pedido), PedidoDTO.class);
		producaoConnector.salvarPedidoBaseLeitura(pedidoDTO);
		return pedidoDTO;
	}

	@Override
	@Transactional
	public PedidoDTO fazerPedidoFake(PedidoDTO pedidoDTO) throws Exception {

		ValidaClienteResponseDTO validaClienteResponseDTO = clienteConnector.validaCliente(pedidoDTO.getCliente());
		if(!validaClienteResponseDTO.isValid()) {
			throw new Exception(validaClienteResponseDTO.getErrorMessage());
		}

		ValidaProdutoResponseDTO validaProdutoResponseDTO = produtoConnector.validaProdutos(
				new ValidaProdutoRequestDTO(pedidoDTO.getProdutos()
					.stream()
					.map(PedidoProdutoDTO::getProduto)
					.collect(Collectors.toList())));
		if(!validaProdutoResponseDTO.isValid()) {
			throw new Exception(validaProdutoResponseDTO.getErrorMessage());
		}

		return salvarPedido(pedidoDTO);
	}

	@Override
	@Transactional
	public PedidoDTO salvarPedidoPreValidacao(PedidoDTO pedidoDTO) throws Exception {
		Pedido pedido = save(new Pedido(LocalDateTime.now(),  StatusPedidoEnum.PENDENTE));
		pedidoDTO.updateNumeroPedido(pedido.getId());
		return pedidoDTO;
	}

	@Override
	@Transactional
	public PedidoDTO salvarPedido(PedidoDTO pedidoDTO) throws Exception {
		Pedido pedido = save(new Pedido(pedidoDTO.getCliente().getId(),
				LocalDateTime.now(),  StatusPedidoEnum.get(pedidoDTO.getStatusPedido())));

		pedidoDTO.updateNumeroPedido(pedido.getId());
		producaoConnector.salvarPedidoBaseLeitura(pedidoDTO);
		return pedidoDTO;
	}

	@Override
	@Transactional
	public void salvarQrCode(Integer id, String qrCode) {
		Pedido pedido = findById(id);
		pedido.setQrCode(qrCode);
		save(pedido);
	}
}