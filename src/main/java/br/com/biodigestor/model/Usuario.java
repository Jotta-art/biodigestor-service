package br.com.biodigestor.model;


import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

@Document(collection = "usuarios")
public class Usuario {
    @Id
    private String id;

    @Indexed(unique = true)
    @NotEmpty(message = "O nome de usuário não pode estar vazio")

    private String username;

    @NotEmpty(message = "A senha não pode estar vazia")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
