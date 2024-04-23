package com.filipe.security.domain.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String login;

    //@Min(6) -> deu erro, possivemente essa Annotation so serve com Integer ou Double
    private String senha;
    private String nome;

    @Transient //Ignora o mapeamento do SpringBoot
    private List<String> permissoes;
}
