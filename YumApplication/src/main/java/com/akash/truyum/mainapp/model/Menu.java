package com.akash.truyum.mainapp.model;

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

}
