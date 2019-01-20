package br.com.borges.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.borges.EstudanteRepository.EstudanteRepository;
import br.com.borges.entity.Estudante;

@RestController
public class EstudanteController {
	
	@Autowired
	EstudanteRepository estudanteRepositiry;
	
	
	
	@RequestMapping(value="/estudante" , method=RequestMethod.GET)
	public List<Estudante> listarEstudantes(){
		return this.estudanteRepositiry.findAll();
	}
	
	
	
	@RequestMapping(value="/estudante" , method=RequestMethod.POST)
	public Estudante salvar(@RequestBody Estudante Estudante) {
		return this.estudanteRepositiry.save(Estudante);
	}
	
	
	@RequestMapping(value="/estudante/{id}" , method=RequestMethod.GET)
	public Estudante getById(@PathVariable String id) {
		return this.estudanteRepositiry.findOne(id);
	}
		
		
	@RequestMapping(value="/estudante/{nome}/nome" , method=RequestMethod.GET)
	public List<Estudante> findByName(@PathVariable String nome) {
		return this.estudanteRepositiry.findByNameLikeIgnoreCase(nome);
	}
	
	
	
	
	

}
