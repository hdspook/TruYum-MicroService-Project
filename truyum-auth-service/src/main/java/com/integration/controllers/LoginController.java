package com.integration.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.integration.util.JwtUtil;

@RestController
@RequestMapping("/api")
public class LoginController {

	@Autowired
	private JwtUtil theJwtTokenUtil;

	//@PostMapping("/authenticate")
	@RequestMapping(value = "/authenticate", consumes = {MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.POST)
	public String createAuthenticationToken(@RequestBody String theDetails){
		System.out.println("Hello There Auth SErvice" + theDetails);
		final String jwt = theJwtTokenUtil.generateToken(theDetails);
		return jwt;

	}

}
