package com.integration.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.integration.util.JwtUtil;

@RestController
@RequestMapping("/api")
public class RequestAuthenticationController {

	@Autowired
	private JwtUtil theTokenUtil;

	@PostMapping("/checkToken")
	public Boolean checkToken(@RequestBody String theToken) {

		return theTokenUtil.validateToken(theToken, "guest");
	}

}
