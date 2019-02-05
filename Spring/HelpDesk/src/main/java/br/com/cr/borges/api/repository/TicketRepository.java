package br.com.cr.borges.api.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.cr.borges.api.entity.Ticket;

public interface TicketRepository extends MongoRepository<Ticket, String>{
	
	/*retorna um tipo page do spring para paginas os resultados*/
	Page<Ticket> findByUsuarioIdOrderByDataDesc(Pageable pages, String UserId);
	
	Page<Ticket> findByTituloIgnoreCaseContainingAndEstadoAndPrioridadeOrderByDataDesc(Pageable pages,String titulo,String estado,String prioridade);
	
	Page<Ticket> findByTituloIgnoreCaseContainingAndEstadoIgnoreCaseContainingAndPrioridadeIgnoreCaseContainingAndUsuarioIdIgnoreCaseContainingOrderByDataDesc(Pageable pages,String titulo,String estado,String prioridade,String usuarioId);
	
	Page<Ticket> findByTituloIgnoreCaseContainingAndEstadoIgnoreCaseContainingAndPrioridadeIgnoreCaseContainingAndUsuarioAtribuidoIdIgnoreCaseContainingOrderByDataDesc(Pageable pages,String titulo,String estado,String prioridade,String usuarioAtribuidoId);
	
	Page<Ticket> findByNumero(Pageable pages,String numero);

}
