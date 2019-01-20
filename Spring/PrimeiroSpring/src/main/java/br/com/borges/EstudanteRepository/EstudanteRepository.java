package br.com.borges.EstudanteRepository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.borges.entity.Estudante;



public interface EstudanteRepository  extends MongoRepository<Estudante, String>{

	
	
}

