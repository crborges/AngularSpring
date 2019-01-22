package br.com.borges.entity;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Estudante {
	
	@Id
	private String id;
	private String nome;
	private int idade;
	private String ocupacao;
	
	
	
	public String getId() 							{return id;					}
	public String getNome() 						{return nome;				}
	public int getIdade() 							{return idade;				}
	public String getOcupacao() 					{return ocupacao;			}

	
	
	public void setId(String id) 					{this.id = id;				}
	public void setNome(String nome) 				{this.nome = nome;			}
	public void setIdade(int idade)			 		{this.idade = idade;		}
	public void setOcupacao(String ocupacao)		{this.ocupacao = ocupacao;	}
	
	
	
	
	
	
	
	
}
