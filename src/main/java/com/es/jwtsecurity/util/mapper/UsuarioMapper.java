package com.es.jwtsecurity.util.mapper;

import com.es.jwtsecurity.dto.UsuarioDTO;
import com.es.jwtsecurity.model.Usuario;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {

    public UsuarioDTO entityToDto (Usuario u) {

        String[] roles = u.getRoles().split(",");
        return new UsuarioDTO(
                u.getUsername(),
                u.getPassword(),
                roles
        );
    }

}
