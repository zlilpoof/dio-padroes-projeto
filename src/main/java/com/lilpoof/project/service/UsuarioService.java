package com.lilpoof.project.service;

import com.lilpoof.project.model.Usuario;

public interface UsuarioService {

	Iterable<Usuario> buscarTodos();

	Usuario buscarPorId(Long id);

	void inserir(Usuario Usuario);

	void atualizar(Long id, Usuario Usuario);

	void deletar(Long id);

}
