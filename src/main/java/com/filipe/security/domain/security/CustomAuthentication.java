package com.filipe.security.domain.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.stream.Collectors;

public class CustomAuthentication implements Authentication {

    private final IdentificacaoUsuario identificacaoUsuario;

    public CustomAuthentication(IdentificacaoUsuario identificacaoUsuario) { //Authentication customizada
        if(identificacaoUsuario == null){
            throw new ExceptionInInitializerError("Não é possivel criar um customAuthentication sem a identificação do usuario");
        }
        this.identificacaoUsuario = identificacaoUsuario;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() { //Retorna autorização de acesso ?
        return this.identificacaoUsuario
                .getPermissoes()
                .stream()
                .map(perm -> new SimpleGrantedAuthority(perm))
                .collect(Collectors.toList());
    }

    @Override
    public Object getCredentials() { //Retorna a senha
        return null;
    }

    @Override
    public Object getDetails() { //Retorna metadados -> Hora do ultimo login, Ip Maquina Entre outro.
        return null;
    }

    @Override
    public Object getPrincipal() { //Identificação do usuário
        return this.identificacaoUsuario;
    }

    @Override
    public boolean isAuthenticated() { //Sempre True
        return true;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException { //Não precisa Chamar
        throw new IllegalArgumentException("Não precisa chamar, pois já estamos autenticados");
    }

    @Override
    public String getName() { //Retorna o nome do usuario(Login)
        return this.identificacaoUsuario.getNome();
    }
}
