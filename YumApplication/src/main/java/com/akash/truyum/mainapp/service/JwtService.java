package com.akash.truyum.mainapp.service;

import org.springframework.stereotype.Service;

@Service
public class JwtService {

	private String jwt;

	public String getJwt() {
		return jwt;
	}

	public void setJwt(String jwt) {
		this.jwt = jwt;
	}

}
