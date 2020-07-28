package com.akash.truyum.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Menu {

	private Integer menuId;
	private String menuItem;
	private Double price;
	// get from context or something to find out whose cart it is

}
