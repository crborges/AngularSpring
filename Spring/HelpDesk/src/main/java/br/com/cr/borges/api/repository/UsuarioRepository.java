package br.com.cr.borges.api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.cr.borges.api.entity.Usuario;

public interface UsuarioRepository extends MongoRepository<Usuario, String>{

	/*usando find by- campo o spring data se vira de fazer o resto
	 *o metodo  findByEmail busca usuarios por emai e quem se preocupa como isso funciona e o proprio spring data
	 */
	Usuario findByEmail(String email);
	
}
