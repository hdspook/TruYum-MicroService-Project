package com.truyum.orderservice.mainapp.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

	private Integer orderId;

	private Integer userId;
	private List<Cart> cart;
	private Double price;

}