package com.akash.userDetails.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.akash.userDetails.models.User;
import com.akash.userDetails.repository.UserRepository;

@Configuration
public class MyUserDetailsService implements UserDetailsService {

	private String role;

	public UserRepository getRepo() {
		return repo;
	}

	public void setRepo(UserRepository repo) {
		this.repo = repo;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Autowired
	UserRepository repo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = repo.findAdminByUsername(username);
		if (user == null) {
			throw new BadCredentialsException("User does not exist");
		}
		user.setRole(role);
		System.out.println(user);
		return new MyRoleDetermination(user);
	}

}
