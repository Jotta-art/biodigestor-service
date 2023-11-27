package br.com.biodigestor.model;

public class Dado {

    private String data;
    private boolean fluxoEntrada;
    private boolean fluxoSaida;
    private Long vazaoEntrada;
    private Long vazaoSaida;
    private Long pressao;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public boolean isFluxoEntrada() {
        return fluxoEntrada;
    }

    public void setFluxoEntrada(boolean fluxoEntrada) {
        this.fluxoEntrada = fluxoEntrada;
    }

    public boolean isFluxoSaida() {
        return fluxoSaida;
    }

    public void setFluxoSaida(boolean fluxoSaida) {
        this.fluxoSaida = fluxoSaida;
    }

    public Long getVazaoEntrada() {
        return vazaoEntrada;
    }

    public void setVazaoEntrada(Long vazaoEntrada) {
        this.vazaoEntrada = vazaoEntrada;
    }

    public Long getVazaoSaida() {
        return vazaoSaida;
    }

    public void setVazaoSaida(Long vazaoSaida) {
        this.vazaoSaida = vazaoSaida;
    }

    public Long getPressao() {
        return pressao;
    }

    public void setPressao(Long pressao) {
        this.pressao = pressao;
    }
}
