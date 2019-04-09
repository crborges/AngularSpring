package br.com.cr.borges.api.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.cr.borges.api.entity.Ticket;

public interface TicketRepository extends MongoRepository<Ticket, String>{
	
	/*
	 acesso a dados de alterações do ticket (DAO) 
	 */

	/*retorna um tipo page do spring para paginar os resultados*/
	
	/*
	 Get by
	 id do usuario
	 ordena pela data decescente
	 @param 
	 	id do usuario
	 */
	Page<Ticket> findByUsuarioIdOrderByDataDesc(Pageable pages, String UserId);

	/*
	Get by
		Titulo do incidente com ignore case e like full
		Estado do ticket 
		Prioridade do ticket 
		Ordena pela data decrescente
	@param
		titulo
	   	estado
	   	prioridade
	*/
	Page<Ticket> findByTituloIgnoreCaseContainingAndEstadoContainingAndPrioridadeContainingOrderByDataDesc(Pageable pages,String titulo,String estado,String prioridade);
	
	/*
	Get by
	 	Titulo do incidente com ignore case e like full
	 	estado inincidente com ignore case e like full
	 	prioridade do incidente com ignore case e like full
	 	id do usuario 
	  	order by data decrescente
	 @param
	 	titulo
	 	estado do incidente
	 	prioridade
	 	id do usuario
	*/
	Page<Ticket> findByTituloIgnoreCaseContainingAndEstadoIgnoreCaseContainingAndPrioridadeIgnoreCaseContainingAndUsuarioIdOrderByDataDesc(Pageable pages,String titulo,String estado,String prioridade,String usuarioId);

	/*
	Get by 
	 	titulo do incidente ignore case like full
	 	estado do incidente ignore case like full
	 	prioridade do incidente ignore case like full
	 	id od usuario a que o incidente foi atribuido com ignore case e like full
	 	order by data decrescente
	 @param
	 	titulo
	 	estado
	 	prioridade
	 	id do usuario atribuido
	*/
	Page<Ticket> findByTituloIgnoreCaseContainingAndEstadoIgnoreCaseContainingAndPrioridadeIgnoreCaseContainingAndUsuarioAtribuidoIdOrderByDataDesc(Pageable pages,String titulo,String estado,String prioridade,String usuarioAtribuidoId);

	/*
	get by numero do incidente @param numero do incidente 
	*/
	
	Page<Ticket> findByNumero(Pageable pages,Integer numero);

}
