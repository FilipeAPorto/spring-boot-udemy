package com.filipe.jparepository.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

@Entity
@Table(name = "produto")
public class Produto {

    @Id //Seta o atributo como ID
    @GeneratedValue(strategy = GenerationType.IDENTITY) //AutoIncremento
    @Column(name = "id") //Caso o nome da coluna seja diferente do nome do atributo.
    private Integer id;

    @Column(name = "descricao")
    @NotBlank
    private String descricao;

    @Column(name = "preco_unitario")
    @Min(0)
    @NotNull
    private BigDecimal preco;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }
}
