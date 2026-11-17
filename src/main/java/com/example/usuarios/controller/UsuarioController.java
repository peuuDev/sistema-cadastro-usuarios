package com.example.usuarios.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

import com.example.usuarios.dto.UsuarioAtualizacaoDTO;
import com.example.usuarios.exceptions.UsuarioNaoEncontradoException;
import com.example.usuarios.model.Usuario;
import com.example.usuarios.service.UsuarioService;

import jakarta.validation.Valid;

@RestController
public class UsuarioController {

	private final UsuarioService service;

	public UsuarioController(UsuarioService service) {
		this.service = service;
	}

	@GetMapping("/usuarios")
	public List<Usuario> listarUsuarios() {
		return service.listarTodos();
	}

	@GetMapping("/usuarios/{id}")
	public ResponseEntity<Usuario> buscarUsuario(@PathVariable Long id) {

	    Usuario usuario = service.buscarPorId(id);

	    return ResponseEntity.ok(usuario);
	}

	@PostMapping("/usuarios")
	public Usuario cadastrarUsuario(@Valid @RequestBody Usuario usuario) {
		return service.cadastrarUsuario(usuario);
	}

	@PutMapping("/usuarios/{id}")
	public ResponseEntity<Usuario> atualizarUsuario(@PathVariable Long id, @Valid @RequestBody Usuario novosDados) {

		Usuario usuario = service.findById(id)
				.orElseThrow(() -> new UsuarioNaoEncontradoException("Usuário não encontrado com ID: " + id));

		usuario.setNome(novosDados.getNome());
		usuario.setSobrenome(novosDados.getSobrenome());
		usuario.setCpf(novosDados.getCpf());

		service.save(usuario);

		return ResponseEntity.ok(usuario);
	}

	@DeleteMapping("/usuarios/{id}")
	public ResponseEntity<Void> excluirUsuario(@PathVariable Long id) {

		if(!service.existsById(id)) {
			throw new UsuarioNaoEncontradoException("Usuário não encontrado com ID: " + id); 
		}
		 
		service.deleteById(id);

		return ResponseEntity.noContent().build();
	}

	@PatchMapping("/usuarios/{id}")
	public ResponseEntity<Usuario> atualizarParcialmente(@PathVariable Long id,
			@Valid @RequestBody UsuarioAtualizacaoDTO dados) {

		Usuario usuario = service.findById(id)
				.orElseThrow(() -> new UsuarioNaoEncontradoException("Usuário não encontrado com ID: " + id));
		
		if (dados.getNome() != null) {
			usuario.setNome(dados.getNome());
		}

		if (dados.getSobrenome() != null) {
			usuario.setSobrenome(dados.getSobrenome());
		}

		if (dados.getCpf() != null) {
			usuario.setCpf(dados.getCpf());
		}

		service.save(usuario);

		return ResponseEntity.ok(usuario);

	}
}
