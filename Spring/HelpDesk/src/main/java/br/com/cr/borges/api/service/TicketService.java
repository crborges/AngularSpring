package br.com.cr.borges.api.service;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import br.com.cr.borges.api.entity.AlteracoesEstado;
import br.com.cr.borges.api.entity.Ticket;

@Component /*indica que é um componente e que sera gerenciado pelo spring*/ 
public interface TicketService {
	
	Ticket createOrUpdate(Ticket ticket);
	Ticket findByid(String id);
	void delete(String id);
	Page<Ticket>listTicket(int page,int count);
	AlteracoesEstado createAlteracaoStatus(AlteracoesEstado alteracao);
	Iterable<AlteracoesEstado>listAlteracoesEstado(String ticketId);
	Page<Ticket> findByUsuarioAtual(int page, int count, String usuarioId);
	Page<Ticket>findByParameters(int page,int count, String titulo,String estado,String prioridade);
	Page<Ticket>findByParametersAndCurrentUser(int page,int count, String titulo,String estado,String prioridade,String usuarioId);
	Page<Ticket> findByNumber(int page, int count, String number);
	Iterable<Ticket>findAll();
	Page<Ticket> findByParametersAndAssignedUser(int page,int count, String titulo,String estado,String prioridade,String usuarioAtribuidoId);
	

}
