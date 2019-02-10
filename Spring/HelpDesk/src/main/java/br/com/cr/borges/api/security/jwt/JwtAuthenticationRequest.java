package br.com.cr.borges.api.security.jwt;

import java.io.Serializable;

public class JwtAuthenticationRequest implements Serializable{
	/*POJO que representa o usuario que esta sendo autenticado é o objeto que transita do reuest da chamada de autienticacao até o response da autenticacao para a apagian onde se insere o token do usuario*/	
	private static final long serialVersionUID = 1L;
	/*email ou login*/
	private String email;
	/*senha da app*/
	private String password;
	
	/*contrutor default*/
	public JwtAuthenticationRequest() {}

	/*construtor que monta todo o objeto de retorno*/
	public JwtAuthenticationRequest(String email, String password) {
		this.setEmail(email);
		this.setPassword(password);
	}
	
	/*geteres e seteres*/
	public void setEmail		(String email) 		{this.email 		= email;		}
	public void setPassword		(String password)	{this.password		= password;		}
	public String getEmail		()				 	{return 			email;			}
	public String getPassword	() 					{return 			password;		}
	
	
	

}
