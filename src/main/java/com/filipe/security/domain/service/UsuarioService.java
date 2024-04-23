package com.filipe.security.domain.service;

import com.filipe.security.domain.entity.Grupo;
import com.filipe.security.domain.entity.Usuario;
import com.filipe.security.domain.entity.UsuarioGrupo;
import com.filipe.security.domain.repository.GrupoRepository;
import com.filipe.security.domain.repository.UsuarioGrupoRepository;
import com.filipe.security.domain.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final GrupoRepository grupoRepository;
    private final UsuarioGrupoRepository usuarioGrupoRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Usuario salvar(Usuario usuario, List<String> grupos) {

        String senhaCriptografada = passwordEncoder.encode(usuario.getSenha());  //Criptografa uma String
        usuario.setSenha(senhaCriptografada);
        usuarioRepository.save(usuario);

        List<UsuarioGrupo> listaUsuarioGrupo = grupos.stream().map(nomeGrupo -> {
            Optional<Grupo> possivelGrupo = grupoRepository.findByNomeGrupo(nomeGrupo);
            if (possivelGrupo.isPresent()) {
                Grupo grupo = possivelGrupo.get();
                return new UsuarioGrupo(usuario, grupo);
            }
            return null;
        }).filter(grupo -> grupo != null).collect(Collectors.toList());

        usuarioGrupoRepository.saveAll(listaUsuarioGrupo);

        return usuario;
    }

    public Usuario obterUsuarioComPermissoes(String login){
        Optional<Usuario> usuarioOptional = usuarioRepository.findByLogin(login);
        if(usuarioOptional.isEmpty()){
            return null;
        }
        Usuario usuario = usuarioOptional.get();
        List<String> permissoes = usuarioGrupoRepository.findPermisooesByUsuario(usuario);
        usuario.setPermissoes(permissoes);

        return usuario;
    }
}
