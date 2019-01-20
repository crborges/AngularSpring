package br.com.borges.EstudanteRepository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.borges.entity.Estudante;



public interface EstudanteRepository  extends MongoRepository<Estudante, String>{

	public List<Estudante> findByNameLikeIgnoreCase(String nome);
		
	
}

