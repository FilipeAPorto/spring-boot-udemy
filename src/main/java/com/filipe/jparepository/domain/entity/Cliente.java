package com.filipe.jparepository.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;


import java.util.List;
import java.util.Set;

@Entity //Entidade JPA
@Table(name = "cliente")  //Caso o nome da Entidade for diferente do nome da tabela no BD (se caso o nome for diferente.
public class Cliente {


    @Id //Seta o atributo como ID
    @GeneratedValue(strategy = GenerationType.IDENTITY) //AutoIncremento
    @Column(name = "id") //Caso o nome da coluna seja diferente do nome do atributo.
    private Integer id;

    @Column(name = "nome", length = 100) //Caso o nome da coluna seja diferente do nome do atributo.
    @NotBlank
    private String nome;

    @Column(name = "cpf", length = 11)
    @NotBlank
    private String cpf;

    @JsonIgnore
    @OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY)
    //Um Cliente para muitos pedidos / mappedBy busca o nome da propriedade que esta mapeado no PEDIDO
    private List<Pedido> pedidos;

    public Cliente() {
    }

    public Cliente(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    @Override
    public String toString() {
        return "Cliente{" + "id=" + id + ", nome='" + nome + '\'' + ", cpf='" + cpf + '\'' + '}';
    }
}
