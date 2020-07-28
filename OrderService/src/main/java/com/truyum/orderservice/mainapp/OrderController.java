package com.truyum.orderservice.mainapp;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.truyum.orderservice.mainapp.model.Order;
import com.truyum.orderservice.mainapp.model.OrderDetails;
import com.truyum.orderservice.mainapp.repository.OrderDetailsRepository;

import lombok.extern.apachecommons.CommonsLog;

@RestController
@RequestMapping("/order")
@CommonsLog

public class OrderController {

	@Autowired
	OrderDetailsRepository repo;

	@PostMapping("/checkout")
	public Order addOrder(@RequestBody Order order) {

		log.info("Reached order details checkout");

		OrderDetails details = new OrderDetails();
		details.setUserId(order.getUserId());

		
		details.setOrderItems(order.getCart().parallelStream().map(i -> i.getMenuItem()).collect(Collectors.toList()));
		repo.save(details);

		return order;
	}

}
