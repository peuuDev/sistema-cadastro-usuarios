package com.example.usuarios.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.usuarios.dto.UsuarioAtualizacaoDTO;
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
	
	public Usuario atualizarUsuario(Long id, Usuario novosDados) {
		Usuario usuario = buscarPorId(id);
		
		usuario.setNome(novosDados.getNome());
		usuario.setSobrenome(novosDados.getSobrenome());
		usuario.setCpf(novosDados.getCpf());
		
		repository.save(usuario);
		
		return usuario;

	}
	
	public Usuario atualizarParcialmente(Long id, UsuarioAtualizacaoDTO novosDados) {
		Usuario usuario = buscarPorId(id);
		
		if(novosDados.getNome() != null) {
			usuario.setNome(novosDados.getNome());
		}
		
		if(novosDados.getSobrenome() != null) {
			usuario.setSobrenome(novosDados.getSobrenome());
		}
		
		if(novosDados.getCpf() != null) {
			usuario.setCpf(novosDados.getCpf());
		}
		
		repository.save(usuario);
		
		return usuario;
		
	}
	
	public void deletarUsuario(Long id) {

	    buscarPorId(id);

	    repository.deleteById(id);
	}
}
