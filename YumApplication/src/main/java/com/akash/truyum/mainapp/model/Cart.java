package com.akash.truyum.mainapp.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Cart {

	@Id
	private Integer menuId;
	private String menuItem;
	private Double price;
	private Integer userId;

}
