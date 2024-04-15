package com.filipe.jparepository.domain.repository;

import com.filipe.jparepository.domain.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Produtos extends JpaRepository<Produto,Integer> {
}
