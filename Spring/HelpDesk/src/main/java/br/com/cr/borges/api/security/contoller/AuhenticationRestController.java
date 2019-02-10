package br.com.cr.borges.api.security.contoller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.cr.borges.api.entity.Usuario;
import br.com.cr.borges.api.security.jwt.JwtAuthenticationRequest;
import br.com.cr.borges.api.security.jwt.JwtTokenUtil;
import br.com.cr.borges.api.security.model.CurrentUser;
import br.com.cr.borges.api.service.UsuarioService;
/*
 REST controller com os metodos responsaveis pela autenticacao do usuario
 so tem exposto o metdo que loga o usuario 
 */

@RestController						/*indica um controller rest*/
@CrossOrigin(origins="*")			/*pode receber requests de qualquer origem*/
public class AuhenticationRestController {

	/*bean injetado do  spring para receber user e senha e autenticar o usuario*/
	@Autowired
	private AuthenticationManager authenticationManager;
	
	/*manipula todo o cliclo de vida do token de autenticação do usuario*/
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	/*injeta o bean do spring que trabalha os detalhes do usuario*/
	@Autowired
	private UserDetailsService userDetailsService;
	
	/*injeta o serviço que manipula a interface do dao de usuario para que sem mexer nas camadas possamos dar um get dos dados do usuario*/
	@Autowired
	private UsuarioService usuarioService;
	
	/*mapeia esse metodo como post para a URL /api/auth*/
	@PostMapping(value="/api/auth")
	public ResponseEntity<?> createAutheticationToken(@RequestBody JwtAuthenticationRequest authenticationRequest) throws AuthenticationException{
		
		/*utiliza os serviços injetados do spring para autenticar o usuario no sistema*/
		final Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						authenticationRequest.getEmail(),
						authenticationRequest.getPassword()
				)
		);
		/*seta no contexto de seguranca da aplicacao a autenticacao do usuario*/
		SecurityContextHolder.getContext().setAuthentication(authentication);
		/*pega os detalhes do usuario via o bena do spring*/		
		final UserDetails userDetails= userDetailsService.loadUserByUsername(authenticationRequest.getEmail());
		/*atraves dos detalhes do usuario que foram retornados vai gerar o seu token de autenticacao */
		final String token =jwtTokenUtil.generateToken(userDetails);
		/*busca o usuario no banco de dados para retornar para a app pois o metodo vai rertornar um usuario*/
		final Usuario usuario = usuarioService.findByEmail(authenticationRequest.getEmail());
		/*seta lixo para nao exibir a senha do usuario na API*/
		usuario.setSenha(null);
		/*Retorna o usuario autenticado para a API*/	
		return ResponseEntity.ok(new CurrentUser(token, usuario));						
	}
	
	
	/*da refresh da autenticacao do usuario qdo for necessario*/
	public ResponseEntity<?> refreshAndGetAuthenticationToken(HttpServletRequest request){
		/*pego o token atual do suario*/
		String token = request.getHeader("Authorizattion");
		/*pego o usuario no aparit do token de autenticacao do usuario*/
		String username= jwtTokenUtil.getUsernameFromToken(token);
		/*vou no meu service mbuscar esse usaurio pelo login que eu retorni*/
		final Usuario usuario = usuarioService.findByEmail(username);
		/*testo via metodo qeu verifica se posso dar refresh no token se da ou não*/
		if(jwtTokenUtil.canTokenBeRefreshed(token)) {
			/*se da para dar refresh no token ok eu regero o token do usuario*/
			String refreshedToken = jwtTokenUtil.refreshToken(token);
			/*retorno o usuario com o novo token para a app*/
			return ResponseEntity.ok(new CurrentUser(refreshedToken, usuario));
		}
		else {
			/*se nao deu para regerar retor no um http-400*/
			return ResponseEntity.badRequest().body(null);
		}
		
			
		
	}
}
