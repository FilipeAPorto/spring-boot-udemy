package com.filipe.security.domain.repository;

import com.filipe.security.domain.entity.Grupo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GrupoRepository extends JpaRepository<Grupo,String> {

    Optional<Grupo> findByNomeGrupo(String nome);
}
