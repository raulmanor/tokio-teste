package com.example.api.conf.security;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UsuarioService implements UserDetailsService{
	
	private Usuario adm = new Usuario("adm", new BCryptPasswordEncoder().encode("1234"));
	
	@Override
	public Usuario loadUserByUsername(String username) throws UsernameNotFoundException {
		if(adm.getNome().equals(username)) return adm;

		throw new UsernameNotFoundException("Usuario " + username + " não foi encontrado");
	}

	public Usuario findByNome(String nomeUsuario) {
		if(adm.getNome().equals(nomeUsuario)) return adm;
		return null;
	}
}
