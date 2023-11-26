package br.com.biodigestor.service;

import br.com.biodigestor.model.Usuario;
import br.com.biodigestor.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.validation.Valid;

@Service
public class UsuarioService implements UserDetailsService {
    @Autowired
    private UsuarioRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = repository.
                findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Login não encontrado."));

        return User
                .builder()
                .username(usuario.getUsername())
                .password(usuario.getPassword())
                .roles("USER")
                .build();
    }

    public void salvar(@Valid Usuario usuario) {
        boolean exists = repository.existsByUsername(usuario.getUsername());
        if (exists) {
            throw new RuntimeException("Usuário já cadastrado");
        }

        repository.save(usuario);
    }
}
