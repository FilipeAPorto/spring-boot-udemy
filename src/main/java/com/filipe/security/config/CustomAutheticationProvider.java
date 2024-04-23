package com.filipe.security.config;

import com.filipe.security.domain.entity.Usuario;
import com.filipe.security.domain.security.CustomAuthentication;
import com.filipe.security.domain.security.IdentificacaoUsuario;
import com.filipe.security.domain.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomAutheticationProvider implements AuthenticationProvider {

    private final UsuarioService usuarioService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String login = authentication.getName();
        String senha = (String) authentication.getCredentials();

        Usuario usuario = usuarioService.obterUsuarioComPermissoes(login);
        if (usuario != null) {
            boolean senhasBatem = passwordEncoder.matches(senha, usuario.getSenha());//Matches serve para verificar se a senha digita bate com a senha encriptada
            if (senhasBatem) {
                IdentificacaoUsuario identificacaoUsuario = new IdentificacaoUsuario(
                        usuario.getId(),
                        usuario.getNome(),
                        usuario.getLogin(),
                        usuario.getPermissoes()
                );
                return new CustomAuthentication(identificacaoUsuario);
            }
        }
        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) { //Sempre True
        return true;
    }
}
