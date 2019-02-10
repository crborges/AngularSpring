package br.com.cr.borges.api.security.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;


/*filtro que intercepta as requisições ao conteiner valida a autenticacao e o token do usuario*/
public class jwtAutheticationTokenFilter extends OncePerRequestFilter{
	
	/*injeta o user detais do spring*/
	@Autowired
	private UserDetailsService userDetails;
	
	/*injeta a classe que manipula o jwt pois precisaremos extrair coisas dele */
	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	/*metodo do filtro*/
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
		/*pega o token do usuario*/
		String authToken = request.getHeader("Authorization");
		/*extrai o usuario de dentro do token*/
		String username= jwtTokenUtil.getUsernameFromToken(authToken);
		/*valida se usario do token is nao null e autenticacao e null*/
		if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
			/*carrega o usuario a atraves do token */
			UserDetails userDetails= this.userDetails.loadUserByUsername(username);
				/*como o cara tem o token o sistema tenta validar o token que foi enviado passadno o token e o user details que ja caregou*/
				if(jwtTokenUtil.validateToken(authToken, userDetails)) {
					/*chama um objeto do spring quie autentica o suario baseado no token*/
					UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails,null, userDetails.getAuthorities());
					/*builda e salva os detalhes da autenticaco do usuaário*/
					authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					/*loga que o suario foi autenticado e poderia ter mais detlahes de como que foi via o token*/
					logger.info("autenticado o usuario "+username+" atraves do contexto de seguranca");
					/*salva no contexto de seguranca a autenticacao do usuario*/
					SecurityContextHolder.getContext().setAuthentication(authentication);
				}
		}
		/*salva o request e response no filtro de autenticacao do usuario*/
		chain.doFilter(request, response);
	}

}
