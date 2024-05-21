package br.com.postech.software.architecture.techchallenge.pedido.repository.jpa;

import br.com.postech.software.architecture.techchallenge.pedido.enums.StatusPedidoEnum;
import br.com.postech.software.architecture.techchallenge.pedido.exception.PersistenceException;
import br.com.postech.software.architecture.techchallenge.pedido.model.Pedido;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoJpaRepository extends JpaRepository<Pedido, Integer> {

	List<Pedido> findByStatusPedidoIdNotIn(List<Integer> statusPedidos, Sort sort) throws PersistenceException;
}
