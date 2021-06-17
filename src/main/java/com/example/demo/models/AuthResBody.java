package com.example.demo.models;

public class AuthResBody {

	private final Boolean authenticated;

	public AuthResBody(Boolean authenticated) {
		this.authenticated = authenticated;
	}

	public Boolean getAuthenticated() {
		return authenticated;
	}

}
