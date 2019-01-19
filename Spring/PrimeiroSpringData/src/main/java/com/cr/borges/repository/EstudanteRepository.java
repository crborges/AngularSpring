package com.cr.borges.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.cr.borges.entity.Estudante;

public interface EstudanteRepository  extends MongoRepository<Estudante, String>{

	
	
}
