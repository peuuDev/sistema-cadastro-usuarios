package com.example.usuarios.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
    
    @PostMapping("/usuarios")
    public Usuario cadastrarUsuario(@RequestBody Usuario usuario) {
        return repository.save(usuario);
    }
}
