package com.lilpoof.project.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lilpoof.project.model.Usuario;
import com.lilpoof.project.model.UsuarioRepository;
import com.lilpoof.project.model.Endereco;
import com.lilpoof.project.model.EnderecoRepository;
import com.lilpoof.project.service.UsuarioService;
import com.lilpoof.project.service.ViaCepService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioRepository UsuarioRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private ViaCepService viaCepService;
	
	@Override
	public Iterable<Usuario> buscarTodos() {
		return UsuarioRepository.findAll();
	}

	@Override
	public Usuario buscarPorId(Long id) {
		Optional<Usuario> Usuario = UsuarioRepository.findById(id);
		return Usuario.get();
	}

	@Override
	public void inserir(Usuario Usuario) {
		salvarUsuarioComCep(Usuario);
	}

	@Override
	public void atualizar(Long id, Usuario Usuario) {
		Optional<Usuario> UsuarioBd = UsuarioRepository.findById(id);
		if (UsuarioBd.isPresent()) {
			salvarUsuarioComCep(Usuario);
		}
	}

	@Override
	public void deletar(Long id) {
		UsuarioRepository.deleteById(id);
	}

	private void salvarUsuarioComCep(Usuario Usuario) {
		String cep = Usuario.getEndereco().getCep();
		Endereco endereco = enderecoRepository.findById(cep).orElseGet(() -> {
			Endereco novoEndereco = viaCepService.consultarCep(cep);
			enderecoRepository.save(novoEndereco);
			return novoEndereco;
		});
		Usuario.setEndereco(endereco);
		UsuarioRepository.save(Usuario);
	}

}
