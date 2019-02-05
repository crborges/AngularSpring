package br.com.cr.borges.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.cr.borges.api.entity.Usuario;
import br.com.cr.borges.api.repository.UsuarioRepository;
import br.com.cr.borges.api.service.UsuarioService;


@Service
public class UsuarioServiceImpl implements UsuarioService{
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public Usuario findByEmail(String email) 					{
		return this.usuarioRepository.findByEmail(email);		}
	
	@Override
	public Usuario criarOuAtualizar(Usuario usuario) 			{
		return this.usuarioRepository.save(usuario);			}
	
	@Override
	public Usuario findById(String id) 							{
		return this.usuarioRepository.findOne(id);				}
	
	@Override
	public void deletar(String id) 								{
		this.usuarioRepository.delete(id);						}
	
	@Override
	public Page<Usuario> findAll(int pagina, int contador) 		{
		Pageable page= new PageRequest(pagina, contador);
		return this.usuarioRepository.findAll(page);			}



}
