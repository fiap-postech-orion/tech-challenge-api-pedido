package br.com.postech.software.architecture.techchallenge.pedido.model;

import br.com.postech.software.architecture.techchallenge.pedido.enums.StatusPedidoEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

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

    @Column(name = "status_pedido_id")
    private Integer statusPedidoId;

    @Column(name = "qr_code")
    private String qrCode;

    @Column(name = "ds_erro")
    private String descricaoErro;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PedidoProduto> produtos = new ArrayList<>();

    public void updateStatus(String status) {
        this.statusPedidoId = StatusPedidoEnum.getByDescricao(status).getValue();
    }

    public void updateStatusEDescricao(String status, String descricaoErro) {
        this.statusPedidoId = StatusPedidoEnum.getByDescricao(status).getValue();
        this.descricaoErro = descricaoErro;
    }

    public Pedido(Long clienteId, LocalDateTime dataPedido, StatusPedidoEnum status) {
        this.clienteId = clienteId;
        this.dataPedido = dataPedido;
        this.statusPedidoId = status.getValue();
    }

    public Pedido(LocalDateTime dataPedido, StatusPedidoEnum status) {
        this.dataPedido = dataPedido;
        this.statusPedidoId = status.getValue();
    }
}
