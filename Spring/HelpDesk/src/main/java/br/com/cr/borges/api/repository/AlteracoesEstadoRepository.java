package br.com.cr.borges.api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.cr.borges.api.entity.AlteracoesEstado;

public interface AlteracoesEstadoRepository extends MongoRepository<AlteracoesEstado, String> {

	Iterable<AlteracoesEstado> findByTicketIdOrderByDataAlteracaoEstadoDesc(String ticketId);
	
}
