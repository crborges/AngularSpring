package br.com.cr.borges.api.enums;


public enum EstadosEnum {

	NOVO,
	DESIGNADO,
	RESOLVIDO,
	APROVADO,
	REPROVADO,
	FECHADO;
	
	public static EstadosEnum getStatus(String estado) {
		switch (estado) {
			case	"NOVO"		:	return NOVO;				
			case	"DESIGNADO"	:	return DESIGNADO;	
			case	"RESOLVIDO"	:	return RESOLVIDO;	
			case	"APROVADO"	:	return APROVADO;	
			case	"REPROVADO"	:	return REPROVADO;	
			case	"FECHADO"	:	return FECHADO;		
			default				:	return null;		
		}
		
	}
	
	
}
