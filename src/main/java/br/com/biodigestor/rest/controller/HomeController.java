package br.com.biodigestor.rest.controller;

import br.com.biodigestor.form.HomeForm;
import br.com.biodigestor.model.Device;
import br.com.biodigestor.service.FirebaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/home")
public class HomeController {
    @Autowired
    private FirebaseService firebaseService;

    @GetMapping
    public List<Device> listar() throws ExecutionException, InterruptedException {
        return this.firebaseService.listar();
    }

    @PostMapping
    public void inserir(@RequestBody HomeForm form) throws ExecutionException, InterruptedException {
        this.firebaseService.inserir(form);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable String id) throws ExecutionException, InterruptedException {
        this.firebaseService.deletar(id);
    }

    @PutMapping("/{id}")
    public void editar(@PathVariable String id, @RequestBody HomeForm form) throws ExecutionException, InterruptedException {
        this.firebaseService.editar(id, form);
    }
}
