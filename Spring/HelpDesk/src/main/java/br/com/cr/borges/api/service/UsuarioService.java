package br.com.cr.borges.api.service;

import org.springframework.data.domain.Page;

import br.com.cr.borges.api.entity.Usuario;

/*interface que interege com o dao ou o reposity da aplicação para dar suporte aos metodos do usuario*/
public interface UsuarioService {

	
	Usuario findByEmail(String email);
	Usuario findById(String id);
	Usuario criarOuAtualizar(Usuario usuario);
	void deletar(String id);
	Page<Usuario> findAll(int pagina, int contador);
}
