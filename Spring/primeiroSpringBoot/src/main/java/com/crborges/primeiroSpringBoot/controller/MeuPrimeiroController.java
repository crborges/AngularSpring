package com.crborges.primeiroSpringBoot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MeuPrimeiroController {


	
	@RequestMapping("/verTxt")
	public String MostrarTexto() {
		return "hello frist spring boot project com o get";
	}
	
	
	@RequestMapping("/")
	public String index() {
		return "Estou na index";
	}
	
	
	@RequestMapping("/login")
	public void index() {
		return "Estou na index";
	}
	
}
