package br.com.biodigestor.rest.controller;

import br.com.biodigestor.form.DadosForm;
import br.com.biodigestor.form.HomeForm;
import br.com.biodigestor.model.Usuario;
import br.com.biodigestor.service.FirebaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/home")
public class HomeController {
    @Autowired
    private FirebaseService firebaseService;

    @PostMapping
    public void salvarDados(@RequestBody DadosForm form) throws ExecutionException, InterruptedException {
        this.firebaseService.salvarDados(form);
    }

    @GetMapping("{username}")
    public Usuario obterDadosUsuarioLogado(@PathVariable String username) throws ExecutionException, InterruptedException {
        return this.firebaseService.obterDadosUsuarioLogado(username);
    }

    @PostMapping("foto-perfil")
    public void salvarFotoPerfil(@RequestBody Usuario usuario) throws ExecutionException, InterruptedException {
        this.firebaseService.salvarFotoPerfil(usuario);
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
