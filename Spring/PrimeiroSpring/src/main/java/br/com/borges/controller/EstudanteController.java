package br.com.borges.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.borges.EstudanteRepository.EstudanteRepository;
import br.com.borges.entity.Estudante;

@RestController
public class EstudanteController {
	
	@Autowired  //anotacoa utilizada a gerenciamento de dependencias do spring para as classes de acessoa o banco 
	EstudanteRepository estudanteRepository;
	
	
	@RequestMapping("/lista")
	public List<Estudante> lista(){
		return this.estudanteRepository.findAll();
	}
	
	@RequestMapping(value="/salva" , method=RequestMethod.POST)
	public Estudante inserir(@RequestBody Estudante estudante){
				return this.estudanteRepository.save(estudante);
		
	}
	
}
