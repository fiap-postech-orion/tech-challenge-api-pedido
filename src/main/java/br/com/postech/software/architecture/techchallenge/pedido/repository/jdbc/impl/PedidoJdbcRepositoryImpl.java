package br.com.postech.software.architecture.techchallenge.pedido.repository.jdbc.impl;

import br.com.postech.software.architecture.techchallenge.pedido.repository.jdbc.IPedidoJdbcRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class PedidoJdbcRepositoryImpl implements IPedidoJdbcRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	protected JdbcTemplate getPersistencia() {
		return jdbcTemplate;
	}
}
