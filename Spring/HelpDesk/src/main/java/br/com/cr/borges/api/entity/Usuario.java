package br.com.cr.borges.api.entity;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import br.com.cr.borges.api.enums.PerfilEnum;

@Document
public class Usuario {

	
	
	@Id
	private String id;
		
	@Indexed(unique=true)
	@NotBlank(message="E-mail Obrigatório")
	@Email(message="E-mail Inválido")
	private String email;
	
	@NotBlank(message="Senha Obrigatória")
	@Size(min=6)
	private String senha;
	
	private PerfilEnum perfil;
	
	
	
	public void setId(String id) 				{this.id 		= id;		}
	public void setEmail(String email)	 		{this.email 	= email;	}
	public void setSenha(String senha) 			{this.senha 	= senha;	}
	public void setPerfil(PerfilEnum perfil) 	{this.perfil	= perfil;	}	
		
	public String getId() 						{return 		id;			}
	public String getEmail() 					{return 		email;		}
	public String getSenha() 					{return 		senha;		}
	public PerfilEnum getPerfil()				{return 		perfil;		}



}



