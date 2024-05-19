package br.com.postech.software.architecture.techchallenge.pedido.service.impl;

import br.com.postech.software.architecture.techchallenge.pedido.configuration.InteiroParaStatusPedidoConverter;
import br.com.postech.software.architecture.techchallenge.pedido.configuration.ModelMapperConfiguration;
import br.com.postech.software.architecture.techchallenge.pedido.configuration.StatusPedidoParaInteiroConverter;
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
import org.modelmapper.TypeToken;
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

		List<Pedido> pedidos = getPersistencia().findByStatusPedidoNotIn(
				Arrays.asList(StatusPedidoEnum.CONCLUIDO, StatusPedidoEnum.CANCELADO),
				Sort.by(Sort.Direction.ASC, "dataPedido")
		);

		MAPPER.typeMap(Pedido.class, PedidoDTO.class)
				.addMappings(mapperA -> mapperA
						.using(new StatusPedidoParaInteiroConverter())
						.map(Pedido::getStatusPedido, PedidoDTO::setStatusPedido))
				.addMappings(mapper -> {
					mapper.map(src -> src.getId(),PedidoDTO::setNumeroPedido);
				});

		return MAPPER.map(pedidos, new TypeToken<List<PedidoDTO>>() {}.getType());
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
	@Transactional
	public PedidoDTO fazerPedidoFake(PedidoDTO pedidoDTO) throws Exception {
		//Obtem os dados do pedido
		MAPPER.typeMap(PedidoDTO.class, Pedido.class)
				.addMappings(mapperA -> mapperA
						.using(new InteiroParaStatusPedidoConverter())
						.map(PedidoDTO::getStatusPedido, Pedido::setStatusPedido));

		Pedido pedido = MAPPER.map(pedidoDTO, Pedido.class);
		pedido.setDataPedido(LocalDateTime.now());
		pedido.setStatusPedido(StatusPedidoEnum.REALIZADO);

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

		pedido = save(pedido);

		MAPPER.typeMap(Pedido.class, PedidoDTO.class)
				.addMappings(mapperA -> mapperA
						.using(new StatusPedidoParaInteiroConverter())
						.map(Pedido::getStatusPedido, PedidoDTO::setStatusPedido))
				.addMappings(mapper -> {
					mapper.map(src -> src.getId(),PedidoDTO::setNumeroPedido);
				});

		producaoConnector.salvarPedidoBaseLeitura(pedidoDTO);
		return pedidoDTO;
	}
}
