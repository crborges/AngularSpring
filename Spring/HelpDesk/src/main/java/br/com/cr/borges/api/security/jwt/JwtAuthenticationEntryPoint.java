package br.com.cr.borges.api.security.jwt;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

/*compoente que atraves do filtros da APP fica monitorando erros na autenticação e se deu algum erro ele devolve http-401 de acesso negado */
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint{

	private static final long serialVerionUID=1L;
	
	
	/*metodoq ue intercepta e devolve http-401 em caso de erro na hora de autenticar o user*/
	@Override
	public void commence(HttpServletRequest request,HttpServletResponse response,AuthenticationException authException) throws IOException{
		
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"Acesso negado");
		
	}

}
