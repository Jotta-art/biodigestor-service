package br.com.biodigestor.to;

public class DocumentoTO {
    private String id;
    private String chave;

    // getters e setters para id e chave

    public DocumentoTO(String id, String chave) {
        this.id = id;
        this.chave = chave;
    }

    // Se preferir, pode adicionar um construtor para receber um documento JSON diretamente
    public DocumentoTO(String json) {
        // Lógica para converter o JSON para a estrutura da classe Documento
    }

    public String getChave() {
        return chave;
    }

    public void setChave(String chave) {
        this.chave = chave;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
