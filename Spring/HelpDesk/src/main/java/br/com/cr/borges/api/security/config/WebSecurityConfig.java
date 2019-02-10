package br.com.cr.borges.api.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.com.cr.borges.api.security.jwt.JwtAuthenticationEntryPoint;
import br.com.cr.borges.api.security.jwt.jwtAutheticationTokenFilter;


/*classe que configura o spring security para dar permissão e acesso aos usuarios*/
@Configuration 											/*indica que esse classe é configuravel*/
@EnableWebSecurity										/*habilita seguranca web*/
@EnableGlobalMethodSecurity(prePostEnabled = true)		/*habilita gobalmente seguranca na aplicação more in https://docs.spring.io/spring-security/site/docs/4.2.6.RELEASE/apidocs/org/springframework/security/config/annotation/method/configuration/EnableGlobalMethodSecurity.html*/
public class WebSecurityConfig 	extends WebSecurityConfigurerAdapter{
	
	/*injeta uma dependecia que esta classe vai usar para trabalhar*/
	@Autowired
	private JwtAuthenticationEntryPoint unauthorizationHandler;
	
	/*injeta a classe de detalhes do usuario que da get do usauri a ser manipulado*/
	@Autowired
	private UserDetailsService userDetailsService;
	
	/*injeta um metodo que buida o user e senha da aplicação*/
	@Autowired
	public void configureAuthentication(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception{
		authenticationManagerBuilder.userDetailsService(this.userDetailsService).passwordEncoder(passwordEncoder());
	}
	
	/*injeta um metodo que da o encode do password do usuario */
	@Bean
	public PasswordEncoder passwordEncoder() {return new BCryptPasswordEncoder();}
	
	
	/*injeta um servico auxiliar para autenticar por filtros e efetuar o que pode e o que não pode passar sem autenticacao*/
	@Bean
	public jwtAutheticationTokenFilter AuthenticationTokenFilterBean() throws Exception  {return new jwtAutheticationTokenFilter();	}
	
	
	
	/*configura como e o que tem que passar pela autenticacao do sistema */
	@Override 
	protected void configure (HttpSecurity httpSecurity) throws Exception{
		/*desabilita a possibilidade troca de informações nao confiaveis entre os clientes e a API inibindo um tipo especifico de aqtaque 
		 more in https://pt.wikipedia.org/wiki/Cross-site_request_forgery 
		*/
		httpSecurity.csrf().disable()
		/*informa que API NÃO VAi barrar que sejam acessados ou exeibidos os seguintes elementos ou seja esles esrao isentos de autenticação
		 /api/auth -> entreypoint da autenticacao
		 ico o icone do site
		 html paginas html
		 css folhas de estilos
		 js java script
		 */
		.exceptionHandling().authenticationEntryPoint(unauthorizationHandler).and()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
		.authorizeRequests()
		.antMatchers(
				HttpMethod.GET,
				"/",
				"/*.html",
				"/favicon.ico",
				"/**/*.html",
				"**/*.css",
				"**/*.js"
		).permitAll()
		.antMatchers("/api/auth/**").permitAll()
		/*quaquer outra coisa que ente ser acessessada só se for autenticado*/
		.anyRequest().authenticated();
		/*mete um filtro de depois de exige que seja validado o token JWT*/
		httpSecurity.addFilterBefore(AuthenticationTokenFilterBean(),UsernamePasswordAuthenticationFilter.class);
		/*habilita o controle de caches de headers */
		httpSecurity.headers().cacheControl();
	}
	
	
	
	

}
