package com.example.api.conf.security;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class Usuario implements UserDetails, Serializable {
	private static final long serialVersionUID = 1L;

	private String nome;
	private String senha;

	public Usuario() {}	
	
	public Usuario(String nome, String senha) {
		this.nome = nome;
		this.senha = senha;
	}

	public String getNome(){
		return nome;
	}

	public void setNome(String nome){
		this.nome = nome;
	}

	public String getSenha(){
		return senha;
	}

	public void setSenha(String senha){
		this.senha = senha;
	}

	// Security
	public static Usuario getUsuarioLogado() {
		return (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}
	
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Usuario)) return false;
		
		return nome.equals(((Usuario)obj).nome);
	}
	
	@Override
	public String toString() {
		return "Usuario [nome=" + nome + ", senha=" + senha + "]";
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return new ArrayList<>();
	}

	@Override
	public String getPassword() {
		return senha;
	}

	@Override
	public String getUsername() {
		return nome;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
