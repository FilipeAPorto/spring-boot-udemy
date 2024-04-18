package com.filipe.jparepository.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "produto")
public class Produto {

    @Id //Seta o atributo como ID
    @GeneratedValue(strategy = GenerationType.IDENTITY) //AutoIncremento
    @Column(name = "id") //Caso o nome da coluna seja diferente do nome do atributo.
    private Integer id;

    @Column(name = "descricao")
    @NotEmpty(message = "Campo Descrição é Obrigatório.")
    private String descricao;

    @Column(name = "preco_unitario")
    @Min(0)
    @NotNull(message = "Campo Preço é obrigatório")
    private BigDecimal preco;

}
