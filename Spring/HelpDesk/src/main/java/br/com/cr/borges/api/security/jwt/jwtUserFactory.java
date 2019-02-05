package br.com.cr.borges.api.security.jwt;

import java.util.ArrayList;
import java.util.List;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import br.com.cr.borges.api.entity.Usuario;
import br.com.cr.borges.api.enums.PerfilEnum;

public class jwtUserFactory {

	private jwtUserFactory() {}
	

	public static jwtUser create(Usuario user) {
		return new jwtUser(user.getId(), user.getEmail(),user.getSenha(),mapToGrantedAuthorities(user.getPerfil()));
	}
	
	
	private static List<GrantedAuthority> mapToGrantedAuthorities(PerfilEnum profileEnun){
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority(profileEnun.toString()));
		return authorities;
	}
}


