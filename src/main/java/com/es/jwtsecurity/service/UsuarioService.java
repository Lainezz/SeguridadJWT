package com.es.jwtsecurity.service;

import com.es.jwtsecurity.dto.UsuarioDTO;
import com.es.jwtsecurity.dto.UsuarioLoginDTO;
import com.es.jwtsecurity.dto.UsuarioRegisterDTO;
import com.es.jwtsecurity.error.exception.BadRequestException;
import com.es.jwtsecurity.error.exception.DuplicateException;
import com.es.jwtsecurity.error.exception.NotFoundException;
import com.es.jwtsecurity.model.Usuario;
import com.es.jwtsecurity.repository.UsuarioRepository;
import com.es.jwtsecurity.security.SecurityConfig;
import com.es.jwtsecurity.util.mapper.UsuarioMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService implements UserDetailsService {

    /**
     * usuarioRepository es un objeto de tipo {@link UsuarioRepository}
     * La instanciación del objeto usuarioRepository viene inyectada automáticamente por Spring Boot
     */
    @Autowired
    private UsuarioRepository usuarioRepository;
    /**
     * passwordEncoder es un objeto de tipo {@link PasswordEncoder}
     * ¿De dónde sale la inicialización de este objeto?
     * La inicialización de este objeto viene dada en la clase {@link SecurityConfig},
     * más concretamente en el método {@link SecurityConfig#passwordEncoder()}
     */
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UsuarioMapper usuarioMapper;

    /**
     * ¡IMPORTANTE!
     * SpringSecurity necesita que implementemos la interfaz {@link UserDetailsService}.
     * La interfaz UsersDetailsService contiene un único método {@link UserDetailsService#loadUserByUsername(String)}
     * El método loadUserByUsername busca en la base de datos al usuario por su userName.
     * Con este método, SpringSecurity tendría toda la configuración necesaria para poder gestionar nuestros Usuarios
     * @param username the username identifying the user whose data is required.
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // BUSCO EL USUARIO POR SU NOMBRE EN LA BDD
        Usuario usuario = usuarioRepository
                .findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario No encontrado"));

        /* RETORNAMOS UN USERDETAILS
        El método loadUserByUsername nos fuerza a devolver un objeto de tipo UserDetails.
        Tenemos que convertir nuestro objeto de tipo Usuario a un objeto de tipo UserDetails
        ¡No os preocupéis, esto es siempre igual!
         */
        List<GrantedAuthority> authorities = Arrays.stream(usuario.getRoles().split(","))
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.trim()))
                .collect(Collectors.toList());

        UserDetails userDetails = User // User pertenece a SpringSecurity
                .builder()
                .username(usuario.getUsername())
                .password(usuario.getPassword())
                .roles(usuario.getRoles())
                .build();

        return userDetails;
    }

    /**
     * Método para registrar un nuevo Usuario en la BDD
     * @param usuarioRegisterDTO
     * @return
     */
    public UsuarioRegisterDTO registerUser(UsuarioRegisterDTO usuarioRegisterDTO) {
        // Comprobamos que el usuario no existe en la base de datos
        if (usuarioRepository.findByUsername(usuarioRegisterDTO.getUsername()).isPresent()) {
            throw new DuplicateException("El nombre de usuario ya existe");
        }

        // Compruebo que ambas contrasenias coinciden
        if(!usuarioRegisterDTO.getPassword1().equals(usuarioRegisterDTO.getPassword2())) {
            throw new BadRequestException("Ambas contraseñas deben ser iguales");
        }

        // Creamos la instancia de
        Usuario newUsuario = new Usuario();

        /*
         La password del newUsuario debe estar hasheada, así que usamos el passwordEncoder que tenemos definido.
         ¿De dónde viene ese passwordEncoder?
         El objeto passwordEncoder está definido al principio de esta clase.
         */
        newUsuario.setPassword(passwordEncoder.encode(usuarioRegisterDTO.getPassword1())); // Hashear la contraseña
        newUsuario.setUsername(usuarioRegisterDTO.getUsername());
        newUsuario.setRoles(usuarioRegisterDTO.getRoles());

        // Guardamos el newUsuario en la base de datos... igual que siempre
        usuarioRepository.save(newUsuario);

        return usuarioRegisterDTO;
    }

    public UsuarioDTO findByNombre(String nombre) {

        Usuario u = usuarioRepository
                .findByUsername(nombre)
                .orElseThrow(() -> new NotFoundException("Usuario con nombre "+nombre+" no encontrado"));

        return usuarioMapper.entityToDto(u);

    }
}
