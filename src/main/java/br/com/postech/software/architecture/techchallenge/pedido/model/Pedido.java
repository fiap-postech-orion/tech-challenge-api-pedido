package br.com.postech.software.architecture.techchallenge.pedido.model;

import br.com.postech.software.architecture.techchallenge.pedido.enums.StatusPedidoEnum;
import br.com.postech.software.architecture.techchallenge.pedido.enums.AssociacaoType;
import br.com.postech.software.architecture.techchallenge.pedido.util.Constantes;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pedido")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Pedido implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "cliente_id")
    private Long clienteId;

    @Column(name = "data_pedido", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime dataPedido;

    @Type(value = AssociacaoType.class,
            parameters = {@Parameter(name = Constantes.ENUM_CLASS_NAME, value = "StatusPedidoEnum")})
    @Column(name = "status_pedido_id")
    private StatusPedidoEnum statusPedido;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PedidoProduto> produtos = new ArrayList<>();

    public void updateStatus(String status) {
        this.statusPedido = StatusPedidoEnum.valueOf(status);
    }
}
