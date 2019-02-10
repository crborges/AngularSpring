package br.com.cr.borges.api.entity;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import br.com.cr.borges.api.enums.EstadosEnum;
import br.com.cr.borges.api.enums.PrioridadeEnum;

@Document
public class Ticket {
	/*@POJO 
  		Representa um incidente que vamos inserir no nosso sistema
	*/

	
	
	@Id
	private String id;
	@DBRef(lazy=true)
	private Usuario usuario;
	private Date data;
	private String titulo;
	private Integer numero;
	private Usuario usuarioAtribuido;
	private String descricao;
	private String imagem;
	private EstadosEnum estado ;
	private PrioridadeEnum prioridade ;
	@Transient /*não precisa criar uma lista dessas alterações no banco de dados eu vou na tabela dele e busco*/
	private List<AlteracoesEstado> alteracoes;
	
	
	
	public void setId(String id)									{this.id 				= id;					}
	public void setUsuario(Usuario usuario)							{this.usuario 			= usuario;				}
	public void setData(Date data)			 						{this.data	 			= data;					}
	public void setTitulo(String titulo) 							{this.titulo 			= titulo;				}
	public void setNumero(Integer numero) 							{this.numero 			= numero;				}
	public void setUsuarioAtribuido(Usuario usuarioAtribuido) 		{this.usuarioAtribuido	= usuarioAtribuido;		}
	public void setDescricao(String descricao) 						{this.descricao 		= descricao;			}
	public void setImagem(String imagem) 							{this.imagem 			= imagem;				}
	public void setEstado(EstadosEnum estado)						{this.estado 			= estado;				}
	public void setPrioridade(PrioridadeEnum prioridade) 			{this.prioridade 		= prioridade;			}
	public void setAlteracoes(List<AlteracoesEstado> alteracoes)	{this.alteracoes 		= alteracoes;			}
	
	
	
	public String getId()											{return 				id;						}
	public Usuario getUsuario()										{return 				usuario;				}
	public Date getData()											{return 				data;					}
	public String getTitulo()										{return 				titulo;					}
	public Integer getNumero()										{return 				numero;					}
	public Usuario getUsuarioAtribuido()							{return 				usuarioAtribuido;		}
	public String getDescricao()									{return 				descricao;				}
	public String getImagem()										{return 				imagem;					}
	public EstadosEnum getEstado() 									{return 				estado;					}
	public PrioridadeEnum getPrioridade() 							{return 				prioridade;				}
	public List<AlteracoesEstado> getAlteracoes() 					{return 				alteracoes;				}



}
