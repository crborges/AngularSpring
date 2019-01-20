package br.com.borges.entity;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Estudante {
	
	@Id
	private int id;
	private String nome;
	private int idade;
	private String ocupacao;
	
	
	
	public int getId()		 					{return id;					}
	public String getName() 					{return nome;				}
	public int getIdade() 						{return idade;				}
	public String getOcupacao() 				{return ocupacao;			}

	
	
	public void setId(int id) 					{this.id = id;				}
	public void setName(String nome)			{this.nome = nome;			}
	public void setIdade(int idade)		 		{this.idade = idade;		}
	public void setOcupacao(String ocupacao)	{this.ocupacao = ocupacao;	}
	
	
	
	
}
