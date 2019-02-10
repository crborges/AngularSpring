package br.com.cr.borges.api.entity;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import br.com.cr.borges.api.enums.EstadosEnum;

@Document
public class AlteracoesEstado {
	/*@POJO 
	  	Representa as varias alteraçoes de estado que um ticket pode ter diurante o seu ciclo de vida
	 */
	
	
	@Id
	private String id;
	@DBRef
	private Ticket ticket;
	@DBRef
	private Usuario usuarioAlteracao;
	private Date dataAlteracaoEstado;
	private EstadosEnum estado;
	
	
	
	public void setId(String id) 									{this.id 					= id;					}
	public void setTicket(Ticket ticket)							{this.ticket 				= ticket;				}
	public void setUsuarioAlteracao(Usuario usuarioAlteracao) 		{this.usuarioAlteracao		= usuarioAlteracao;		}
	public void setDataAlteracaoEstado(Date dataAlteracaoEstado)	{this.dataAlteracaoEstado 	= dataAlteracaoEstado;	}
	public void setEstado(EstadosEnum estado) 						{this.estado 				= estado;				}
	
	
	
	public String getId() 											{return 					id;						}
	public Ticket getTicket() 										{return 					ticket;					}
	public Usuario getUsuarioAlteracao() 							{return 					usuarioAlteracao;		}
	public Date getDataAlteracaoEstado() 							{return 					dataAlteracaoEstado;	}
	public EstadosEnum getEstado()				 					{return 					estado;					}
	
	
	
	
}

