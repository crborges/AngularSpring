package br.com.cr.borges.api.security.model;

import br.com.cr.borges.api.entity.Usuario;

public class CurrentUser {

	private String token; 
	private Usuario usuario;
	
	
	public CurrentUser(String token, Usuario usuario) {
		this.setToken(token);
		this.setUsuario(usuario);
	}


	
	
	public void setToken		(String token) 		{this.token 	= token;	}
	public void setUsuario		(Usuario usuario) 	{this.usuario	= usuario;	}
	public String getToken		() 					{return token;				}
	public Usuario getUsuario	() 					{return usuario;			}


	
	
}
