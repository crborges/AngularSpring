package br.com.cr.borges.api.dto;

import java.io.Serializable;

public class Resumo implements Serializable{


	private static final long serialVersionUID = 1L;
	private  Integer quantidadeNovos;
	private  Integer quantidadeResolvidos;
	private  Integer quantidadeAprovados;
	private  Integer quantidadeDesaprovados;
	private  Integer quantidadeAtribuidos;
	private  Integer quantidadeFechados;
	
	
	public void  setQuantidadeNovos(Integer quantidadeNovos) 				{this.quantidadeNovos = quantidadeNovos;				}
	public void setQuantidadeResolvidos(Integer quantidadeResolvidos) 		{this.quantidadeResolvidos = quantidadeResolvidos;		}
	public void setQuantidadeAprovados(Integer quantidadeAprovados) 		{this.quantidadeAprovados = quantidadeAprovados;		}
	public void setQuantidadeDesaprovados(Integer quantidadeDesaprovados)	{this.quantidadeDesaprovados = quantidadeDesaprovados;	}
	public void setQuantidadeAtribuidos(Integer quantidadeAtribuidos) 		{this.quantidadeAtribuidos = quantidadeAtribuidos;		}
	public void setQuantidadeFechados(Integer quantidadeFechados) 			{this.quantidadeFechados = quantidadeFechados;			}
	
	public Integer getQuantidadeNovos()									 	{return quantidadeNovos;								}
	public Integer getQuantidadeResolvidos() 								{return quantidadeResolvidos;							}
	public Integer getQuantidadeAprovados() 								{return quantidadeAprovados;							}
	public Integer getQuantidadeDesaprovados() 								{return quantidadeDesaprovados;							}
	public Integer getQuantidadeAtribuidos() 								{return quantidadeAtribuidos;							}
	public Integer getQuantidadeFechados() 									{return quantidadeFechados;								}
	
	
	
	


	
}
