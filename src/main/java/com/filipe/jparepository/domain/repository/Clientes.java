package com.filipe.jparepository.domain.repository;

import com.filipe.jparepository.domain.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface Clientes extends JpaRepository<Cliente, Integer> {

    List<Cliente> findByNomeContaining(String nome);  //Colocar a propriedade q eu preciso buscar, findBy + Nome + Like

    @Query("select c from Cliente c left join fetch c.pedidos where c.id = :id ") //left -> se tiver ou não pedidos ele vai trazer, sem isso ele so traz se tiver pedido
    Cliente findClienteFetchPedidos(@Param("id") Integer id);


/*
    @Query(value = "select c from Cliente c where c.nome like :nome ")
    List<Cliente> encotrarPorNome(@Param("nome") String nome);

    List<Cliente> findByNomeOrId(String nome, Integer id);*/


}