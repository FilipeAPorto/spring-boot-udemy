package com.filipe.jparepository.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity //Entidade JPA
@Table(name = "cliente")  //Caso o nome da Entidade for diferente do nome da tabela no BD (se caso o nome for diferente.
public class Cliente {


    @Id //Seta o atributo como ID
    @GeneratedValue(strategy = GenerationType.IDENTITY) //AutoIncremento
    @Column(name = "id") //Caso o nome da coluna seja diferente do nome do atributo.
    private Integer id;

    @Column(name = "nome", length = 100) //Caso o nome da coluna seja diferente do nome do atributo.
    @NotEmpty(message = "Campo nome é obrigatório.")
    private String nome;

    @Column(name = "cpf", length = 11)
    @NotEmpty(message = "Campo CPF é obrigatório.")
    @CPF(message = "Informe um CPF Válido.")
    private String cpf;

    @JsonIgnore
    @OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY)
    //Um Cliente para muitos pedidos / mappedBy busca o nome da propriedade que esta mapeado no PEDIDO
    private List<Pedido> pedidos;


    public Cliente(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }
}
