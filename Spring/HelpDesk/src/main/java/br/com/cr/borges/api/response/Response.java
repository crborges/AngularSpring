package br.com.cr.borges.api.response;

import java.util.ArrayList;
import java.util.List;

/*
 classe que padroniza o  retorno que a minha API vai ter retornando um objeto generico e uma lista de rrs se tiver 
 todos os meus metodos vao retorena rum tipo dessa classe ai 
 */
public class Response<T> {

	private T dado;
	private List<String> erros;
	
	
	public void setDado(T dado) {this.dado = dado;}
	public void setErros(List<String> erros) {this.erros = erros;}


	public T getDado() {return dado;}
	/*o get de erros inicailiza o array para evirtar que de merda porue napode nao ter erros para retornar*/
	public List<String> getErros() {
		if(this.erros==null) {this.erros= new ArrayList<String>();}
		return erros;
	}

	
	
}
