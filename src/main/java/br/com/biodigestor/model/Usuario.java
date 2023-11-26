package br.com.biodigestor.model;


import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

public class Usuario {
    @Id
    private String id;

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
