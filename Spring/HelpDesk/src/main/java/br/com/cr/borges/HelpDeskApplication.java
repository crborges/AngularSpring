package br.com.cr.borges;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import br.com.cr.borges.api.entity.Usuario;
import br.com.cr.borges.api.enums.PerfilEnum;
import br.com.cr.borges.api.repository.UsuarioRepository;

@SpringBootApplication
public class HelpDeskApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelpDeskApplication.class, args);
	}
	
	@Bean
	CommandLineRunner iniciar(UsuarioRepository usuarioRepository,PasswordEncoder passwordEncoder) {
		return args ->{
			initUsers(usuarioRepository, passwordEncoder);
		};
		
	}
	
	private void initUsers(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
		Usuario admin = new Usuario();
		admin.setEmail("mail@mail.com");
		admin.setSenha(passwordEncoder.encode("123456"));
		admin.setPerfil(PerfilEnum.ROLE_ADMIN);
		
		Usuario getter = usuarioRepository.findByEmail("mail@mail.com");
		if(getter==null) {
			usuarioRepository.save(admin);
		}
		
		
	}

}

