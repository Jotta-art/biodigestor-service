package br.com.biodigestor.model;


import javax.validation.constraints.NotEmpty;

public class Usuario {
    @NotEmpty(message = "O nome de usuário não pode estar vazio")
    private String username;

    @NotEmpty(message = "O email não pode estar vazio")
    private String email;

    @NotEmpty(message = "A senha não pode estar vazia")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
