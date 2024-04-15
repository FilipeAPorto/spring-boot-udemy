package com.filipe.jparepository.domain.repository;


import com.filipe.jparepository.domain.entity.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItensPedido extends JpaRepository<ItemPedido,Integer> {
}
