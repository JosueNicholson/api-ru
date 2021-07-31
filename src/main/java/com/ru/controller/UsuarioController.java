package com.ru.controller;



import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ru.model.Usuario;
import com.ru.model.UsuarioSignUp;
import com.ru.security.JwtRequest;
import com.ru.security.JwtTokenUtil;
import com.ru.service.UsuarioService;


@RequestMapping("")
@RestController
public class UsuarioController {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private UsuarioService userDetailsService;
	
	
	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<String> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
		final UserDetails userDetails = userDetailsService
				.loadUserByUsername(authenticationRequest.getUsername());
		final String token = "Token: "+jwtTokenUtil.generateToken(userDetails);
		return ResponseEntity.ok(token);
	}
	
	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}

	@RequestMapping(value = "/test", method = RequestMethod.POST)
	public ResponseEntity<Usuario> signUp(@RequestBody UsuarioSignUp userSignUp) throws Exception {
		Usuario user = new Usuario(userSignUp.getUsername(), userSignUp.getPassword());

		return ResponseEntity.ok(userDetailsService.save(user));
	}
}
