package com.example.usuarios.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

import com.example.usuarios.model.Usuario;
import com.example.usuarios.repository.UsuarioRepository;

@RestController
public class UsuarioController {

    private final UsuarioRepository repository;

    public UsuarioController(UsuarioRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/usuarios")
    public List<Usuario> listarUsuarios() {
        return repository.findAll();
    }
    
    @GetMapping("/usuarios/{id}")
    public ResponseEntity<Usuario> buscarUsuario(@PathVariable Long id) {

        Optional<Usuario> usuario = repository.findById(id);

        if (usuario.isPresent()) {
            return ResponseEntity.ok(usuario.get());
        }

        return ResponseEntity.notFound().build();
    }
    
    @PostMapping("/usuarios")
    public Usuario cadastrarUsuario(@RequestBody Usuario usuario) {
        return repository.save(usuario);
    }
    
    @PutMapping("/usuarios/{id}")
    public ResponseEntity<Usuario> atualizarUsuario(
            @PathVariable Long id,
            @RequestBody Usuario novosDados) {

        Optional<Usuario> usuarioExistente = repository.findById(id);

        if (usuarioExistente.isPresent()) {

            Usuario usuario = usuarioExistente.get();

            usuario.setNome(novosDados.getNome());
            usuario.setSobrenome(novosDados.getSobrenome());
            usuario.setCpf(novosDados.getCpf());

            repository.save(usuario);

            return ResponseEntity.ok(usuario);
        }

        return ResponseEntity.notFound().build();
    }
    
    @DeleteMapping("/usuarios/{id}")
    public ResponseEntity<Void> excluirUsuario(@PathVariable Long id) {

        if (repository.existsById(id)) {
            repository.deleteById(id);

            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }
    
    @PatchMapping("/usuarios/{id}")
    public ResponseEntity<Usuario> atualizarParcialmente(
            @PathVariable Long id,
            @RequestBody Map<String, Object> dados) {

        Optional<Usuario> usuarioExistente = repository.findById(id);

        if (usuarioExistente.isPresent()) {

            Usuario usuario = usuarioExistente.get();

            if (dados.containsKey("nome")) {
                usuario.setNome((String) dados.get("nome"));
            }

            if (dados.containsKey("sobrenome")) {
                usuario.setSobrenome((String) dados.get("sobrenome"));
            }

            if (dados.containsKey("cpf")) {
                usuario.setCpf((String) dados.get("cpf"));
            }

            repository.save(usuario);

            return ResponseEntity.ok(usuario);
        }

        return ResponseEntity.notFound().build();
    }
}
