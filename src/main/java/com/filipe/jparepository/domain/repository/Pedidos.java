package com.filipe.jparepository.domain.repository;

import com.filipe.jparepository.domain.entity.Cliente;
import com.filipe.jparepository.domain.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Pedidos extends JpaRepository<Pedido,Integer> {

    List<Pedido> findByCliente(Cliente cliente);
}
