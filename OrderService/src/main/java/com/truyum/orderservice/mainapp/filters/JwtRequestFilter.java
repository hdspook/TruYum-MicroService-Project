package com.truyum.orderservice.mainapp.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.OncePerRequestFilter;

import lombok.extern.apachecommons.CommonsLog;

@Configuration
@CommonsLog
public class JwtRequestFilter extends OncePerRequestFilter {


	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		log.info("Entering doFilterInternal method");
		final String authorizationHeader = request.getHeader("Authorization");

		String jwt = null;

		if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
			jwt = authorizationHeader.substring(7);
			
			if(jwt != null) {
				chain.doFilter(request, response);
				
			}
			
		}else {
			throw new RuntimeException("Authorization is required via Jwt");
		}

	}

}