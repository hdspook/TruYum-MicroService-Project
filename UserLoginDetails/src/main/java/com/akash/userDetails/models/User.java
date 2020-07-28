package com.akash.userDetails.models;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
@ToString
public class User {
	@Id
	private Integer userId;
	private String username;
	private String password;
	private String role;
}
