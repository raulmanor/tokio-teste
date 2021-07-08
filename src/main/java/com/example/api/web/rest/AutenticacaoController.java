package com.example.api.web.rest;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.api.conf.security.TokenService;
import com.example.api.domain.Login;
import com.example.api.domain.Token;


@RestController
@RequestMapping("autenticacao")
public class AutenticacaoController {

	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private TokenService tokenService;
	

	@PostMapping
	public Token autenticar(@RequestBody @Valid Login loginDto) {
		try {
			Authentication authentication = authManager.authenticate(loginDto.converter());
			String token = tokenService.gerarToken(authentication);
			Token tokenDto = new Token(token, "Bearer");
			
			return tokenDto;
		} catch (AuthenticationException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "LoginDto not found");
		}
	}	
}
