package br.com.cr.borges.api.security.jwt;

import java.io.Serializable;

public class JwtAuthenticationRequest implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String email;
	private String password;
	
	public JwtAuthenticationRequest() {}

	public JwtAuthenticationRequest(String email, String password) {
		this.setEmail(email);
		this.setPassword(password);
	}
	
	public void setEmail		(String email) 		{this.email 		= email;		}
	public void setPassword		(String password)	{this.password		= password;		}
	public String getEmail		()				 	{return 			email;			}
	public String getPassword	() 					{return 			password;		}
	
	
	

}
