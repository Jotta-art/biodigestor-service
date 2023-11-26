package br.com.biodigestor.model;

public class Device {
    private String Id;
    private String data;
    private String pressao;
    private String vazao;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getPressao() {
        return pressao;
    }

    public void setPressao(String pressao) {
        this.pressao = pressao;
    }

    public String getVazao() {
        return vazao;
    }

    public void setVazao(String vazao) {
        this.vazao = vazao;
    }
}
