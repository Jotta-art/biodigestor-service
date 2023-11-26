package br.com.biodigestor.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("devices")
public class Documento {

    @Id
    private String id;
    private String chave;
    private String nome;
    private String vazao;
    private String pressao;
    private String data;

    public Documento(String chave, String nome) {
        this.chave = chave;
        this.nome = nome;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getChave() {
        return chave;
    }

    public void setChave(String chave) {
        this.chave = chave;
    }

    public String getVazao() {
        return vazao;
    }

    public void setVazao(String vazao) {
        this.vazao = vazao;
    }

    public String getPressao() {
        return pressao;
    }

    public void setPressao(String pressao) {
        this.pressao = pressao;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
