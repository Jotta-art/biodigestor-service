package br.com.biodigestor.rest.controller;

import br.com.biodigestor.form.HomeForm;
import br.com.biodigestor.model.Documento;
import br.com.biodigestor.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/home")
public class HomeController {
    @Autowired
    private HomeService service;

    @GetMapping
    public List<Documento> listar() {
        return this.service.listar();
    }

    @PostMapping
    public void inserir(@RequestBody HomeForm form) {
        this.service.inserirNome(new Documento("chave", form.getNome()));
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable String id) {
        this.service.deletar(id);
    }

    @PutMapping("/{id}")
    public void editar(@PathVariable String id, @RequestBody HomeForm form) {
        this.service.editar(id, form);
    }
}
