package com.akash.truyum.mainapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.akash.truyum.mainapp.proxy.AuthServiceProxy;
import com.akash.truyum.mainapp.service.JwtService;

@Controller
public class TestController {

	@Autowired
	private AuthServiceProxy theAuthProy;

	@Autowired
	private JwtService jwtService;

	@GetMapping("/")
	@ResponseBody
	public String testing() {

		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		String token = theAuthProy.createAuthenticationToken(username);
		jwtService.setJwt(token);
		return "Hello User";

	}

}
