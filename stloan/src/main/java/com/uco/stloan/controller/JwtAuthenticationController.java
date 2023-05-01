package com.uco.stloan.controller;

import com.uco.stloan.Services.Person.PersonService;
import com.uco.stloan.Services.jwt.JwtUserDetailsService;
import com.uco.stloan.dto.PersonDTO;
import com.uco.stloan.exception.ResourceNotFound;
import com.uco.stloan.exception.Unauthorized;
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
import org.springframework.validation.BindingResult;
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


	@PostMapping("/authenticate")
	public ResponseEntity<Response> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest, BindingResult result) throws Exception{
		authenticate(authenticationRequest.getEmail(), authenticationRequest.getPassword(), result);

		if(result.hasErrors()){
				throw new Unauthorized("UNAUTHORIZE USER", result);

		}

		final UserDetails userDetails = userDetailsService
				.loadUserByUsername(authenticationRequest.getEmail());

		final String token = jwtTokenUtil.generateToken(userDetails);


		return Response.createResponse(HttpStatus.OK, new JwtResponse(token));


	}

	@PostMapping("/register")
	public ResponseEntity<Response> saveUser(@RequestBody PersonDTO person)  {

		//aqui falta validar que el correo no se repita
		Person newPerson = new Person(person.getIdentification(), person.getName(), person.getLastname(),
				person.getEmail(), person.getPassword(), person.getMobile(), person.getAddress(), person.getRol(),
				person.getRFID());

		return Response.createResponse(HttpStatus.CREATED, userDetailsService.save(newPerson));
	}

	private void 	authenticate(String username, String password, BindingResult result) throws Exception {
		Objects.requireNonNull(username);
		Objects.requireNonNull(password);

		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new ResourceNotFound("USER_DISABLED");
		} catch (BadCredentialsException e) {
			throw new Unauthorized("INVALID_CREDENTIALS", result);
		}
	}
}
