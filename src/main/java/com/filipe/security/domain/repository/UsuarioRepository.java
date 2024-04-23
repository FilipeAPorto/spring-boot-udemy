package com.filipe.security.domain.repository;

import com.filipe.security.domain.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario,String> {
    Optional<Usuario> findByLogin(String login);//"Login" tem q ser igual ao atributo da classe em questão, se caso colocar email -> fingByEmail
}
