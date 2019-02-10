package br.com.cr.borges.api.filter;
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
 
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
 
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class SimpleCORSFilter implements Filter {
	/*Filtro para passar os adequados cabeçalhos CORS para que a aplicação faça o login de boas e aceite requisições de qualquer lugar */	
	private final Log logger = LogFactory.getLog(this.getClass());
 
	@Override
	/*No init loga que bootou a classe*/
	public void init(FilterConfig fc) throws ServletException {logger.info("HelpDesk-API | SimpleCORSFilter loaded");}
 
	@Override
	/*aplica o filtro de CORS a todas as requisições executadas*/
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		/*pega request e response */
		HttpServletResponse response = (HttpServletResponse) resp;
		HttpServletRequest request = (HttpServletRequest) req;
		/*seta o cabeçalho de que permite requisiçoes de qualquer lugar(dava para limitrar de um dominio x so que ficariamos limitados a esse dominio)*/
		response.setHeader("Access-Control-Allow-Origin", "*");
		/*indica quais o metodos http estão disponivies para serem chamados*/		
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT");
		/*indica o tempo que os dados da requsisição ficam dipsoniveis no cache*/
		response.setHeader("Access-Control-Max-Age", "3600");
		/*indica quais os cabecalhos são aceitos na minha requisição o que pode ser enviado e que o cors vai aceirtar */
		response.setHeader("Access-Control-Allow-Headers", "x-requested-with, authorization, Content-Type, Authorization, credential, X-XSRF-TOKEN");
		
		/*se as options que recebi estão ok ele retorna http-200*/
		if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
			response.setStatus(HttpServletResponse.SC_OK);
		}
		/*se nao estiver ok ele retorna o request e response que estiverm disponiveis via o filtro*/
		else {
			chain.doFilter(req, resp);
		}
 
	}
 
	@Override
	/*sobrescriuta obrigatoria do mestdo que destoi o filtro criado*/
	public void destroy() {	}
 
}