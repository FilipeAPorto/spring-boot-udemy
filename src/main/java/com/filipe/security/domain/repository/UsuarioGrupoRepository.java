package com.filipe.security.domain.repository;


import com.filipe.security.domain.entity.Usuario;
import com.filipe.security.domain.entity.UsuarioGrupo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UsuarioGrupoRepository extends JpaRepository<UsuarioGrupo,String> {

    @Query("""            
            select distinct g.nomeGrupo
            from UsuarioGrupo ug
            join ug.grupo g
            join ug.usuario u 
            where u = ?1            
            """)
    List<String> findPermisooesByUsuario(Usuario usuario);
}
