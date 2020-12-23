package com.appeventos.appeventos.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import com.appeventos.appeventos.models.Usuario;
import com.appeventos.appeventos.repository.IUsuarioRepository;

@Repository
public class ImplementsUserDetailsService implements UserDetailsService {
	
	@Autowired
	private IUsuarioRepository ur;

	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		Usuario usuario = ur.findByLogin(login);
		if(usuario == null)
			throw new UsernameNotFoundException("Usuário não encontrado.");
		return usuario;
	}

}
