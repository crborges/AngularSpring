package br.com.cr.borges.api.contoller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mongodb.DuplicateKeyException;

import br.com.cr.borges.api.entity.Usuario;
import br.com.cr.borges.api.response.Response;
import br.com.cr.borges.api.service.UsuarioService;

/*transforma a classe num controller rest */
@RestController
/*o endpoint de acesso que pache chegar nessa clase va ser /api/usuario*/
@RequestMapping("/api/usuario")
/*libera acesso e qualque rporta e de qualquer serrvidsor e de qualquer lugar*/
@CrossOrigin(origins="*")
public class UsuarioController {
	
	/*para servir o modelo do usario*/
	@Autowired
	private UsuarioService usuarioService;
	/*para encodar o passaword*/	
	@Autowired
	private PasswordEncoder passwordEncoder;


	
	
	
	/*POST	 	- Salva usuario 	/api/usuario 				JSON	Roles ADMIN		*/
	/*PUT 		- Atualiza usuario	/api/usuario				JSON	Roles ADMIN		*/
	/*GET 		- Busca um			/api/usuario/{id}					Roles ADMIN		*/
	/*DELETE 	- Deleta terceiro	/api/usuario/{id}					Roles ADMIN		*/
	/*GET		- Retorna Paginado	/api/usuario/{page}/{count}			Roles ADMIN		*/
	
	
	/*maepado o metodo para o verbo POST*/
	@PostMapping
	/*valida para que somenbte um user do tipo admin  crie um novo usuario*/
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
		/*a anotação @requestbody como parametro ffaz com que suy so aceita da interface se for mandando um tipo usuario*/
		/*response entity é o tipo de resposta que o spring da para as resqusições rest*/
		public ResponseEntity<Response<Usuario>> create(HttpServletRequest request, @RequestBody Usuario usuario, BindingResult result){
		/*cria um tipo de response padronizado com um usuario dentreo para retorenar*/
		Response<Usuario> response = new Response<Usuario>();
		/*try catch padrão para pegar e tratar os meus erros*/
		try {
			/*cham o meu metod quer valida  usaurio que esta vindo la nesse metodo eu vou socar no resposne erros se aocntecem no coso o unico erro e de sem email mais doeria validar outras coisas no serivodsr sse quisesse como cpf ser somente nuemro etc....*/
			validarCriacaoUsuario(usuario, result);
			/*se o metod de malidar oq eu foi mandao tem erros dentro vou pegar toos os erros que tem dentro para devolver esses erros(sem email cpf nao e cpf e por ai vai)*/
			if(result.hasErrors()) {
				result.getAllErrors().forEach(error ->response.getErros().add(error.getDefaultMessage()));
				/*rewtorno um bad request para o chamador informando que deu merda*/
				return ResponseEntity.badRequest().body(response);
			}
			/*ser passou da validacao vai comecar a criar o usuario*/
			/*vai pegar a senha recebido em trexto puro e encodar para salvar no banco escondida*/
			usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
			/*chama a service de usauirio para salvar o usuario e retornar o casa salvo para o chamador*/
			Usuario persistido = (Usuario) usuarioService.criarOuAtualizar(usuario);
			/*seta no meu reposne padrão do sistema o uusuario que foi salvo */
			response.setDado(persistido);
		} catch (DuplicateKeyException duplicado) { 
			/*cathc para retornar no caso de chave duplicada*/
			response.getErros().add("Chave duplicada");
			return ResponseEntity.badRequest().body(response);
		}catch (Exception e) { 
			/*o famos catach de erro inesperado que podia tbem ja socar o trace em algum lugar*/
			response.getErros().add("ocorreu um erro inesperado e msg eh "+e.getMessage());
			/*retorna o corpo da solictacoa com erro*/
			return ResponseEntity.badRequest().body(response);
		}
		/*se checou aqui ja passou do meu try e ta tuydo certo vai retornar um 200 com o usuario criado*/
		return ResponseEntity.ok(response);
	}

	
	
	/*vai responder ao verbo put do http para dar update é bem parecido com o salvar mesmo*/
	@PutMapping
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	public ResponseEntity<Response<Usuario>> update(HttpServletRequest request, @RequestBody Usuario usuario, BindingResult result){
		Response<Usuario> response = new Response<Usuario>();
		try {
			validarAtualizacaoUsuario(usuario, result);
			if(result.hasErrors()) {
				result.getAllErrors().forEach(error ->response.getErros().add(error.getDefaultMessage()));
				return ResponseEntity.badRequest().body(response);
			}
			usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
			Usuario atualizado = (Usuario) usuarioService.criarOuAtualizar(usuario);
			response.setDado(atualizado);
		}catch (Exception e) { 
			response.getErros().add("ocorreu um erro inesperado e msg eh "+e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
		return ResponseEntity.ok(response);
	}
	

	
	@GetMapping(value="{id}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	public ResponseEntity<Response<Usuario>> findById(@PathVariable("id") String id){
		Response<Usuario> response = new Response<Usuario>();
		Usuario usuario = usuarioService.findById(id);
		if(usuario == null) {
			response.getErros().add("usuario nao localizado com o id "+id);
			return ResponseEntity.badRequest().body(response);
		}
		response.setDado(usuario);
		return ResponseEntity.ok(response);
	}

	
	
	@DeleteMapping(value="{id}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	public ResponseEntity<Response<String>> delete(@PathVariable("id") String id){
		Response<String> response = new Response<String>();
		Usuario usuario = usuarioService.findById(id);
		if(usuario == null) {
			response.getErros().add("usuario nao localizado para delecao com o id "+id);
			return ResponseEntity.badRequest().body(response);
		}
		usuarioService.deletar(id);
		return ResponseEntity.ok(new Response<String>());
	}

	
	
	@GetMapping(value="{page}/{count}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	public ResponseEntity<Response<Page<Usuario>>> findAll(@PathVariable int page, @PathVariable int count){
		Response<Page<Usuario>> response = new Response<Page<Usuario>>();
		Page<Usuario> usuarios = usuarioService.findAll(page, count);
		response.setDado(usuarios);
		return ResponseEntity.ok(response);
	}

	
	/*VALIDADORES DOS SERVIÇOS*/
	/*aqui nesse metodo ao criar um usuario eu chamo todos os tipos de validacao que quero fazer como se tem email slogin se o cpf e cpf etc....*/
	private void validarCriacaoUsuario(Usuario usuario,BindingResult result) {
		/*para mostrar so vou validar se rtem o email do cara*/
		if(usuario.getEmail()==null) {result.addError(new ObjectError("Usuario", "obrigatório informar o email")); }
	}

	
	
	private void validarAtualizacaoUsuario(Usuario usuario,BindingResult result) {
		if(usuario.getId()==null) {result.addError(new ObjectError("Usuario", "inforamr o id para poder aualizar o usuario")); }
		if(usuario.getEmail()==null) {result.addError(new ObjectError("Usuario", "obrigatório informar o email")); }
	}
	
}
