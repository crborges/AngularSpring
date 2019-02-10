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
	
	/*classe padrão de boot de uma aplicação spring  o metodo main da classe chama o runner interno do spring e incia a APP*/
	public static void main(String[] args) {SpringApplication.run(HelpDeskApplication.class, args);}
	
	
	/*
	  Metodo que não é o padrão do spring na incialização que esta aqui para iniciar alguma coisa na 
	  aplicação como por exempo um serviço ou uma classe que não possa ser injetada registros no banco etc...
	  
	  Aqui ela esta para chamar um metodo que inserte um user no banco de dados para que eu possa logsar visto que nem umn  controle de useres eu tenho ainda	
	*/
	@Bean
	CommandLineRunner iniciar(UsuarioRepository usuarioRepository,PasswordEncoder passwordEncoder) {
		return args ->{
			initUsers(usuarioRepository, passwordEncoder);
		};
		
	}
	
	
	/*
	  metodo que nos criamos para criar um usuario para poder autenticar na aplicação visto que 
	  so temos a classe de repository de usuarios
	 */
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

