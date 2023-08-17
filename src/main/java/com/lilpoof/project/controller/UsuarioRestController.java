package com.lilpoof.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lilpoof.project.model.Usuario;
import com.lilpoof.project.service.UsuarioService;

@RestController
@RequestMapping("Usuarios")
public class UsuarioRestController {

	@Autowired
	private UsuarioService UsuarioService;

	@GetMapping
	public ResponseEntity<Iterable<Usuario>> buscarTodos() {
		return ResponseEntity.ok(UsuarioService.buscarTodos());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Usuario> buscarPorId(@PathVariable Long id) {
		return ResponseEntity.ok(UsuarioService.buscarPorId(id));
	}

	@PostMapping
	public ResponseEntity<Usuario> inserir(@RequestBody Usuario Usuario) {
		UsuarioService.inserir(Usuario);
		return ResponseEntity.ok(Usuario);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Usuario> atualizar(@PathVariable Long id, @RequestBody Usuario Usuario) {
		UsuarioService.atualizar(id, Usuario);
		return ResponseEntity.ok(Usuario);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		UsuarioService.deletar(id);
		return ResponseEntity.ok().build();
	}
}
