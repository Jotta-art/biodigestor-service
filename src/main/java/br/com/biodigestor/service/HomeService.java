package br.com.biodigestor.service;

import br.com.biodigestor.form.HomeForm;
import br.com.biodigestor.model.Documento;
import br.com.biodigestor.repository.DocumentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HomeService {
    private final DocumentoRepository documentoRepository;



    @Autowired
    public HomeService(DocumentoRepository documentoRepository) {
        this.documentoRepository = documentoRepository;
    }

    public List<Documento> listar() {
        try {
            return documentoRepository.findAll();
        } catch (Exception e) {
            System.err.println("Erro ao inserir documento: " + e.getMessage());
            throw new RuntimeException("Erro ao inserir documento: " + e.getMessage());
        }
    }

    public void inserirNome(Documento document) {
        try {
            documentoRepository.save(document);
        } catch (Exception e) {
            System.err.println("Erro ao inserir documento: " + e.getMessage());
        }
    }

    public void deletar(String id) {
        try {
            Documento documento = documentoRepository.findById(id).orElseThrow(() -> new RuntimeException("Não foi possível excluir o item selecionado"));
            documentoRepository.delete(documento);
        } catch (Exception e) {
            System.err.println("Erro ao deletar documento: " + e.getMessage());
        }
    }

    public void editar(String id, HomeForm form) {
        try {
            Documento documento = documentoRepository.findById(id).orElseThrow(() -> new RuntimeException("Não foi possível editar o item selecionado"));
            documento.setNome(form.getNome());
            documentoRepository.save(documento);
        } catch (Exception e) {
            System.err.println("Erro ao editar documento: " + e.getMessage());
        }
    }
}
