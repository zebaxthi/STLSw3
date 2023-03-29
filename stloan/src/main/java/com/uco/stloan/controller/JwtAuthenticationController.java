package com.uco.stloan.controller;

import com.uco.stloan.Services.Persona.PersonService;
import com.uco.stloan.Services.jwt.JwtUserDetailsService;
import com.uco.stloan.jwt.JwtTokenUtil;
import com.uco.stloan.model.JwtRequest;
import com.uco.stloan.model.JwtResponse;
import com.uco.stloan.model.Person;
import com.uco.stloan.web.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;


@RestController
@CrossOrigin
public class JwtAuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private JwtUserDetailsService userDetailsService;

	@Autowired
	private UserDetailsService jwtInMemoryUserDetailsService;

	@Autowired
	private PersonService personService;

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest)
			throws Exception {
		try{
			authenticate(authenticationRequest.getEmail(), authenticationRequest.getPassword());
		}catch (Exception e){
			return Response.createResponse(HttpStatus.UNAUTHORIZED, e.getMessage());
		}

		final UserDetails userDetails = userDetailsService
				.loadUserByUsername(authenticationRequest.getEmail());

		final String token = jwtTokenUtil.generateToken(userDetails);

		return Response.createResponse(HttpStatus.OK, new JwtResponse(token));
	}

	@PostMapping("/register")
	public ResponseEntity<Response> saveUser(@RequestBody Person person)  {
		Person personDB = personService.findByEmail(person.getEmail());
		return Response.createResponse(HttpStatus.CREATED, userDetailsService.save(person));
	}

	private void 	authenticate(String username, String password) throws Exception {
		Objects.requireNonNull(username);
		Objects.requireNonNull(password);

		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
}
