package com.akash.truyum.mainapp.model;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
@ToString
public class User {
	private Integer userId;
	private String password;
	private String role;
}
