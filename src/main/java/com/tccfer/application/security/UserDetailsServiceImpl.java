package com.tccfer.application.security;

import java.util.List;
import java.util.stream.Collectors;

import com.tccfer.application.model.entity.pessoa.UsuarioSistema;
import com.tccfer.application.model.repository.usuariorepository.UsuarioSistemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    private final UsuarioSistemaRepository userRepository;

    public UserDetailsServiceImpl(UsuarioSistemaRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UsuarioSistema user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Não existe este usuário: " + username);
        } else {
            return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getSenha(),
                    getAuthorities(user));
        }
    }

    private static List<GrantedAuthority> getAuthorities(UsuarioSistema user) {
        return user.getTipoUsuario().stream().map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                .collect(Collectors.toList());

    }

    public String encodePassword(String password) {
        String encodedPassword = passwordEncoder.encode(password);
       return encodedPassword;
    }
}
