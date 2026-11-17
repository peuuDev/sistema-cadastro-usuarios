package com.example.usuarios.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.usuarios.exceptions.UsuarioNaoEncontradoException;
import com.example.usuarios.model.Usuario;
import com.example.usuarios.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	private final UsuarioRepository repository; 
	
	public UsuarioService(UsuarioRepository repository) {
		this.repository = repository;
	}
	
	public List<Usuario> listarTodos(){
		return repository.findAll();
	}
	
	public Usuario buscarPorId(Long id) {
		Usuario usuario = repository.findById(id)
				.orElseThrow(()-> new UsuarioNaoEncontradoException("Usuário não encontrado com ID: " + id));
		
		return usuario;
	}
	
	public Usuario cadastrarUsuario(Usuario usuario) {
		return repository.save(usuario);
	}
}
