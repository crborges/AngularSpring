package br.com.cr.borges.api.security.jwt;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint{

	private static final long serialVerionUID=1L;
	
	@Override
	public void commence(HttpServletRequest request,HttpServletResponse response,AuthenticationException authException) throws IOException{
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"Acesso negado");
	}

}
