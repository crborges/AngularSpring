package br.com.cr.borges.api.security.jwt;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
/*componente que da suporte a usdo do token do Spring*/
@Component
public class JwtTokenUtil implements Serializable{

	/*chaves para man ipulacao do token*/
	private static final long serialVersionUID 	= 1L;
	static final String CLAIM_KEY_USERNAME		="sub";
	static final String CLAIM_KEY_CREATED		="created";
	static final String CLAIM_KEY_EXPIRED		="exp";
	
	/*busca o nome da app no application properties */
	@Value("${jwt.secret}")
	private String secret;
	/*busca no application properties o tempo do cliclo de vida do token*/
	@Value("${jwt.expiration}")
	private Long expiration;

	/*extrai de dentro do token o usuario que esta la dentro*/
	public String getUsernameFromToken(String token) {
		String username;
		try {
			final Claims claims= getClaimsFromToken(token);
			username= claims.getSubject();
		} catch (Exception e) {
			username= null;
		}
		return username;
	}
	
	
	
	/*extrai de dentro do token a data de expiração do token*/
	public Date getExpirationDateFromToken(String token) {
		Date expiration;
		try {
			final Claims claims= getClaimsFromToken(token);
			expiration= claims.getExpiration();
		} catch (Exception e) {
			expiration= null;
		}
		return expiration;
	}
	
	
	/*extrair os claims que nso sao nada mais nada menso qeu um mapeamento de um objeto json de que o token é feito apra um hash map java */
	public Claims getClaimsFromToken(String token) {
		Claims claims;
		/*builda o token apartir do nome da api da string do token o result é um hash map dos valores do json para o claims*/
		try {
			claims=	Jwts.parser()
						.setSigningKey(secret)
						.parseClaimsJws(token)
						.getBody();
		} catch (Exception e) {
			claims= null;
		}
		return claims;
	}
	
	
	/*extrai a data de expiracao do token e verifica se o token ainda esta valido*/
	private Boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}
	
	/* receber o objeto user detais do spring e apar deles gera o hsah map chavfe valor necessario para gerar o token e passa isso para o metodoq ue gera o token em si*/ 
	public String generateToken(UserDetails userDetails) {
		Map<String,Object> claims = new HashMap<>();
		claims.put(CLAIM_KEY_USERNAME, userDetails.getUsername());
		final Date creattedDate= new Date();
		claims.put(CLAIM_KEY_CREATED, creattedDate);
		return doGenerateToken(claims);
	}
	
	
	
	/*metodo que recebe o hsah map de chaves valor e vai gerar o token*/
	public String doGenerateToken(Map<String, Object> claims) {
		/*retira a chave data gerada do hash map e cria um date disso*/
		final Date createdDate= (Date)claims.get(CLAIM_KEY_CREATED);
		/*cria a darta de expiracao do token pegando a data criada e soamndo a ele em milissegundo o tempo definido de duração do token*/
		final Date expirationDate = new Date(createdDate.getTime()+expiration*1000);
		/*pega os dadso do hash a data de expiração e qual o algoritimo e a cahve de criptografia a ser usdada e builda o token no formato json para transformar o json e uma string de dadosa base */
		return Jwts.builder()
					.setClaims(claims)
					.setExpiration(expirationDate)
					.signWith(SignatureAlgorithm.HS512, secret)
					.compact();
	}
	
	
	
	
	public Boolean canTokenBeRefreshed(String token) {
		return (!isTokenExpired(token));
	}
	
	
	
	public String refreshToken(String token) {
		String refreshedToken;
		try {
			final Claims claims = getClaimsFromToken(token);
			claims.put(CLAIM_KEY_CREATED, new Date());
			refreshedToken= doGenerateToken(claims);
			
		} catch (Exception e) {
			refreshedToken=null;
		}
		return refreshedToken;
	}
	
	
	
	public Boolean validateToken(String token, UserDetails userDetails) {
		jwtUser user= (jwtUser) userDetails;
		final String username= getUsernameFromToken(token);
		return(username.equals(user.getUsername())&& !isTokenExpired(token));
	}
	
	
	
	
	
	
	
}
