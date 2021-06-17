package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.example.demo.service.MyUserDetailsService;

@Component
public class MyAuthProvider implements AuthenticationProvider {

	@Autowired
	private MyUserDetailsService userDetailsService;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		System.out.println("\nIn MyAuthProvider.authenticate(): ");	
		
		// Get the User from UserDetailsService
		String providedUsername = authentication.getPrincipal().toString();
		UserDetails user = userDetailsService.loadUserByUsername(providedUsername);
		System.out.println("User Details from UserService based on username-" + providedUsername + " : " + user);
		
		String providedPassword = authentication.getCredentials().toString();
		String correctPassword = user.getPassword();
		
		System.out.println("Provided Password - " + providedPassword + " Correct Password: " + correctPassword);
		
		// Authenticate 
		// If Passwords don't match throw and exception
		if(!providedPassword.equals(correctPassword))
			throw new RuntimeException("Incorrect Credentials");
		
		System.out.println("Passwords Match....\n");
		
		// return Authentication Object
		Authentication authenticationResult = 
				new UsernamePasswordAuthenticationToken(user, authentication.getCredentials(), user.getAuthorities());
		return authenticationResult;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		System.out.println("\nIn MyAuthProvider.supports(): ");	
		System.out.println("Checking whether MyAuthProvider supports Authentication type\n");
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
