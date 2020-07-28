package com.akash.userDetails.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.akash.userDetails.exceptions.CustomException;
import com.akash.userDetails.models.AuthenticationResponse;
import com.akash.userDetails.models.User;
import com.akash.userDetails.repository.UserRepository;
import com.akash.userDetails.service.MyUserDetailsService;
import com.akash.userDetails.util.JwtUtil;

import lombok.extern.apachecommons.CommonsLog;

@RestController
@CommonsLog
@RequestMapping("/user")
public class UserLoginController {

	@Autowired
	UserRepository repo;

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	MyUserDetailsService myUserDetails;

	@Autowired
	JwtUtil jwtUtil;

	@PostMapping("/create")
	public User createUser(@RequestBody User user) {
		log.info("Entering the create method");
		return repo.save(user);
	}

	@RequestMapping("/greet")
	public String greet(Principal principal) {
		log.info("Entering the greet method");
		return "Hello, " + principal.getName();
	}

	@PutMapping("/update/{id}")
	public User updateUser(@PathVariable("id") String id, @RequestBody User user) throws CustomException {
		log.info("Entering the update method");
		if (repo.findById(id).get() == null) {
			throw new CustomException("ID is not found");
		}
		return repo.save(user);
	}

	@DeleteMapping("/delete/{id}")
	public String delete(@PathVariable("id") String id, Principal user) throws CustomException {
		log.info("Entering the delete method");
		if (id.equals(user.getName())) {
			log.warn("Admin cannot delete self!");
			throw new CustomException("Cannot delete self");
		}
		log.info("Successfully deleted user: " + id);
		repo.deleteById(id);
		return "User successfully deleted";

	}

	@GetMapping("/authenticate")
	public ResponseEntity<?> login(@RequestBody User user) throws BadCredentialsException {
		myUserDetails.setRole(repo.getAuthority(String.valueOf(user.getUserId())));
		try {
			authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(user.getUserId(), user.getPassword()));
		} catch (BadCredentialsException e) {
			throw new BadCredentialsException("Incorrect Username or password");
		}
		final UserDetails userDetails = myUserDetails.loadUserByUsername(String.valueOf(user.getUserId()));
		final String jwt = jwtUtil.generateToken(userDetails);
		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}

	@RequestMapping("/findDetails/{jwtToken}")
	public User getAllDetails(@PathVariable("jwtToken") String jwtToken) {
		log.info("Entering find details method");
		User user = new User();
		user.setUserId(Integer.parseInt(jwtUtil.extractUsername(jwtToken)));
		user.setUsername(repo.findUsernameById(user.getUserId()));
		user.setPassword("[CONFIDENTIAL]");
		String role = repo.getAuthority(String.valueOf(user.getUserId()));
		user.setRole(role);
		return user;
	}

	@RequestMapping("/find")
	public List<User> getAllUsers() {
		log.info("Entering the find method");
		return repo.findAll();
	}

}
