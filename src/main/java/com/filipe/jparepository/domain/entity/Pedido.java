package com.filipe.jparepository.domain.entity;

import com.filipe.jparepository.domain.enums.StatusPedido;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "pedido")
public class Pedido {

    @Id //Seta o atributo como ID
    @GeneratedValue(strategy = GenerationType.IDENTITY) //AutoIncremento
    @Column(name = "id") //Caso o nome da coluna seja diferente do nome do atributo.
    private Integer id;

    @ManyToOne //Muitos pedidos para 1 cliente
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;  //mappedBy aqui

    @Column(name = "data_pedido")
    @NotNull
    private LocalDate dataPedido;

    @Column(name = "total", precision = 20, scale = 2) // Digitos totais = 20 , quantas casas decimais = 2
    @NotNull
    private BigDecimal total;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StatusPedido status; //Forma de criar Status para confirmação

    @OneToMany(mappedBy = "pedido")
    private List<ItemPedido> itens;

}
