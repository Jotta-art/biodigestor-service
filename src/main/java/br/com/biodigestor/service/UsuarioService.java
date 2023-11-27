package br.com.biodigestor.service;

import br.com.biodigestor.model.Usuario;
import br.com.biodigestor.rest.exception.UsernameNotFoundException;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

import static java.util.Objects.isNull;

@Service
public class UsuarioService implements UserDetailsService {
    private final FirebaseServiceHelper firebaseServiceHelper;

    @Autowired
    public UsuarioService(FirebaseServiceHelper firebaseServiceHelper) {
        this.firebaseServiceHelper = firebaseServiceHelper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        CollectionReference usersCollection = firebaseServiceHelper.getusuariosCollection();

        Query query = usersCollection.whereEqualTo("username", username);
        ApiFuture<QuerySnapshot> querySnapshot = query.get();

        Usuario usuario = null;

        try {
            for (DocumentSnapshot document : querySnapshot.get().getDocuments()) {
                usuario = document.toObject(Usuario.class);
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }

        if (isNull(usuario)) {
            throw new UsernameNotFoundException(HttpStatus.UNAUTHORIZED, "Login não encontrado.");
        }

        return User
                .builder()
                .username(usuario.getUsername())
                .password(usuario.getPassword())
                .roles("USER")
                .build();
    }

    public void salvarUsuario(Usuario usuario) throws ExecutionException, InterruptedException {
        boolean exists = firebaseServiceHelper.existsByUsername(usuario.getUsername());
        if (exists) {
            throw new RuntimeException("Usuário já cadastrado");
        }

        CollectionReference usuarios = firebaseServiceHelper.getusuariosCollection();

        ApiFuture<DocumentReference> result = usuarios.add(usuario);
        System.out.println("ID do Documento Criado: " + result.get().getId());
    }
}
