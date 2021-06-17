package com.example.demo.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.AuthReqBody;
import com.example.demo.models.AuthResBody;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private AuthenticationManager authManager;
	
	@PostMapping("/login")
	public AuthResBody authenticate(@RequestBody AuthReqBody authReqBody) {
		System.out.println("Auth Details: " + authReqBody);
				
		UsernamePasswordAuthenticationToken token = new 
				UsernamePasswordAuthenticationToken(
						authReqBody.getUsername(), 
						authReqBody.getPassword());
		
		System.out.println("\nAuthentication Token Before Authentication: " + token);
		
		Authentication authResult = authManager.authenticate(token);
		
		System.out.println();
		System.out.println("Authentication Token After Authentication: " + authResult);
		System.out.println();
		
		System.out.println("Authentication Token in Security Context: " + SecurityContextHolder.getContext().getAuthentication());
		
		System.out.println();
		if(authResult.isAuthenticated())
			System.out.println("User is Authenticated");
		
		return new AuthResBody(true);
	}

}
