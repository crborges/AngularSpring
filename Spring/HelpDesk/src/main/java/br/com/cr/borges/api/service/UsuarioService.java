package br.com.cr.borges.api.service;

import org.springframework.data.domain.Page;

import br.com.cr.borges.api.entity.Usuario;

public interface UsuarioService {

	
	Usuario findByEmail(String email);
	Usuario findById(String id);
	Usuario criarOuAtualizar(Usuario usuario);
	void deletar(String id);
	Page<Usuario> findAll(int pagina, int contador);
}
