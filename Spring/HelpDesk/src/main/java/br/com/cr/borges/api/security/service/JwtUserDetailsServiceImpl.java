package br.com.cr.borges.api.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.authentication.UserServiceBeanDefinitionParser;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.cr.borges.api.entity.Usuario;
import br.com.cr.borges.api.security.jwt.jwtUserFactory;
import br.com.cr.borges.api.service.UsuarioService;

@Service
public class JwtUserDetailsServiceImpl  implements UserDetailsService{


	
	/*carrega o service do usuario para  poder recupersar ele depois*/
	@Autowired
	private UsuarioService usuarioService;
	
	/*sobrescreve o loadusuario do spring para a minha realidade onde tenhoq ue buiscar o usuario por email ja o do spring e so um inerface que rpecisa de uma construção concreta*/
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Usuario user	= usuarioService.findByEmail(email);
		if(user == null) {
			throw new UsernameNotFoundException(String.format("Usuario não encontrado com o login %S",email));
		}
		else {
			return jwtUserFactory.create(user);
		}

	}

}
