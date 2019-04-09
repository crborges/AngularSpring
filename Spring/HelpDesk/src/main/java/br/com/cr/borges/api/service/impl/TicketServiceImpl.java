package br.com.cr.borges.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.cr.borges.api.entity.AlteracoesEstado;
import br.com.cr.borges.api.entity.Ticket;
import br.com.cr.borges.api.repository.AlteracoesEstadoRepository;
import br.com.cr.borges.api.repository.TicketRepository;
import br.com.cr.borges.api.service.TicketService;

@Service
public class TicketServiceImpl implements TicketService {

	@Autowired
	private TicketRepository ticketRepository;
	
	@Autowired
	private AlteracoesEstadoRepository alteracoesEstadoRepository;

	
	/*OPERACOES BASICAS DO CRUD*/
	@Override
	public Ticket createOrUpdate(Ticket ticket)									{return this.ticketRepository.save(ticket);															}
	@Override
	public Ticket findByid(String id) 											{return this.ticketRepository.findOne(id);															}
	@Override
	public void delete(String id) 												{this.ticketRepository.delete(id);																	}
	@Override
	public AlteracoesEstado createAlteracaoStatus(AlteracoesEstado alteracao) 	{return this.alteracoesEstadoRepository.save(alteracao);											}
	@Override
	public Iterable<AlteracoesEstado> listAlteracoesEstado(String ticketId) 	{return this.alteracoesEstadoRepository.findByTicketIdOrderByDataAlteracaoEstadoDesc(ticketId);		}
	@Override
	public Iterable<Ticket> findAll()											{return this.ticketRepository.findAll();															}
	@Override
	public Page<Ticket> listTicket(int page, int count) 						{Pageable pages = new PageRequest(page, count); return this.ticketRepository.findAll(pages);		}

	
	
	
	/*BUSCAS COMPLEXAS DO TICKET POR DONO QUEM PEGOU OU POR TITULO NUMERO */
	@Override
	public Page<Ticket> findByUsuarioAtual(int page, int count, String usuarioId) 																			{
		Pageable pages = new PageRequest(page, count); 
		return this.ticketRepository.findByUsuarioIdOrderByDataDesc(pages, usuarioId);					
	}
	@Override
	public Page<Ticket> findByParameters(int page, int count, String titulo, String estado, String prioridade) 												{
		Pageable pages = new PageRequest(page, count); 	
		return this.ticketRepository.findByTituloIgnoreCaseContainingAndEstadoContainingAndPrioridadeContainingOrderByDataDesc(pages, titulo, estado, prioridade);			
	}
	
	@Override
	public Page<Ticket> findByParametersAndCurrentUser(int page, int count, String titulo, String estado,String prioridade, String usuarioId)				{
		Pageable pages = new PageRequest(page, count); 
		return this.ticketRepository.findByTituloIgnoreCaseContainingAndEstadoIgnoreCaseContainingAndPrioridadeIgnoreCaseContainingAndUsuarioAtribuidoIdOrderByDataDesc(pages, titulo, estado, prioridade, usuarioId);
	}
	@Override
	public Page<Ticket> findByNumber(int page, int count, Integer numero)																					{
		Pageable pages = new PageRequest(page, count); 
		return this.ticketRepository.findByNumero(pages, numero);
	}
	@Override
	public Page<Ticket> findByParametersAndAssignedUser(int page, int count, String titulo, String estado,String prioridade, String usuarioAtribuidoId) 	{
		Pageable pages = new PageRequest(page, count); 
		return this.ticketRepository.findByTituloIgnoreCaseContainingAndEstadoIgnoreCaseContainingAndPrioridadeIgnoreCaseContainingAndUsuarioAtribuidoIdOrderByDataDesc(pages, titulo, estado, prioridade, usuarioAtribuidoId);
	}

}
