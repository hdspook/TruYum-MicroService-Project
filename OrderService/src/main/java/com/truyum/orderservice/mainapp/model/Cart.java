package com.truyum.orderservice.mainapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cart {

	private Integer menuId;
	private String menuItem;
	private Double price;
	private Integer userId;

}
