package com.akash.truyum.mainapp.controller;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.akash.truyum.mainapp.model.Cart;
import com.akash.truyum.mainapp.model.Menu;
import com.akash.truyum.mainapp.model.Order;
import com.akash.truyum.mainapp.repository.CartRepository;
import com.akash.truyum.mainapp.service.JwtService;

import lombok.extern.apachecommons.CommonsLog;

@Controller
@CommonsLog
@RequestMapping("/yum")
public class YumController {

	@Autowired
	RestTemplate template;

	// user details from service

	Integer userId = null;
	String userRole = "USER";

	@Autowired
	CartRepository cartRepository;

	@Autowired
	private JwtService jwtService;

	@Value("${url}")
	String url;

	@GetMapping("/greet")
	@ResponseBody
	public String greet(HttpServletRequest request) {
		log.info("Greeting the user");
		System.out.println(request.getHeader("Authorization"));
		return "Hello, User!";
	}

	@GetMapping("/find")
	public String showAllMenuItems(Model model) {
//		User user = getUserDetails(request);
//		userId = user.getUserId();
		log.info("Entering the /find method in YumController");
		HttpEntity<String> entity = getEntity();
		ResponseEntity<Menu[]> output = template.exchange(url + "/find", HttpMethod.GET, entity, Menu[].class);

		log.info("Finding the menu items from ");
		List<Menu> menu = Arrays.asList(output.getBody());
		model.addAttribute("menuItems", menu);
		return "allMenuItems";
	}

	@GetMapping("/find/{id}")
	public String showById(@PathVariable("id") Integer id, Model model) {
		HttpEntity<String> entity = getEntity();
		ResponseEntity<Menu[]> menu = template.exchange(url + "/find/", HttpMethod.GET, entity, Menu[].class);
		//Menu menu = template.getForObject(url + "/find/" + id, Menu.class);
		System.out.println(menu);
		model.addAttribute("menu", menu.getBody()[0]);
		return "findOneItem";
	}

	@GetMapping("/addToCart/{Id}")
	public String addingToCart(@PathVariable("Id") Integer id, Model model, Principal principal) {
		// String authorizationHeader = request.getHeader("Authorization").substring(7);
		userId = Integer.parseInt(principal.getName());
		HttpEntity<String> entity = getEntity();
		ResponseEntity<Menu[]> menu = template.exchange(url + "/find/", HttpMethod.GET, entity, Menu[].class);
		//Menu menu = template.getForObject(url + "/find/" + id, Menu.class);
		Cart cart = new Cart();
		cart.setMenuId(id);
		cart.setMenuItem(menu.getBody()[id-1].getMenuItem());
		cart.setPrice(menu.getBody()[id-1].getPrice());
		cart.setUserId(userId);
		Cart saved = cartRepository.save(cart);
		model.addAttribute("item", saved);
		List<Cart> allCartItems = cartRepository.findAllByUserId(userId);
		model.addAttribute("cartItems", allCartItems);
		Double total = allCartItems.parallelStream().mapToDouble(i -> i.getPrice()).sum();
		model.addAttribute("total", total);
		return "redirect:/yum/cart";
	}

	@GetMapping("/cart")

	public String showCart(Model model) {
		log.info("Entering the cart method");
		List<Cart> allCartItems = cartRepository.findAllByUserId(userId);
		log.info("Finding all items using userId" + userId);
		model.addAttribute("cartItems", allCartItems);
		Double total = allCartItems.parallelStream().mapToDouble(i -> i.getPrice()).sum();
		model.addAttribute("total", total);
		log.info("Redirecting to: cartPage.jsp");
		return "cartPage";
	}

	@GetMapping("/removeFromCart/{id}")
	public String removingFromCart(@PathVariable("id") Integer id) {
		log.info("Entering the deleteFromCart() method");
		cartRepository.deleteByMenuItemId(id);
		log.info("Redirecting to allMenuItems.jsp");
		return "redirect:/yum/find";
	}

	@GetMapping("/clearCart")
	public void clearCart() {
		cartRepository.deleteAll();
	}

	@GetMapping("/checkout")
	@ResponseBody
	public String checkingOut() {

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		System.out.println("CHECKING OUT");

		List<Cart> allCartItems = cartRepository.findAllByUserId(userId);
		Order order = new Order();
		order.setCart(allCartItems);
		order.setUserId(userId);
		order.setPrice(allCartItems.parallelStream().mapToDouble(i -> i.getPrice()).sum());
		HttpEntity<Order> entity = getEntityForCart(order);
		//ResponseEntity<Order> myOrder = template.postForEntity("http://localhost:8102/order/checkout", order, entity, Order.class);
		Order myOrder = template.postForObject("http://localhost:8102/order/checkout", entity, Order.class);
		clearCart();
		return myOrder.toString();

	}

	private HttpEntity<Order> getEntityForCart(Order order) {
		HttpHeaders http = new HttpHeaders();
		String token = null;
		if (jwtService.getJwt() != null)
			token = jwtService.getJwt();
		http.add("Authorization", "Bearer " + token);
		HttpEntity<Order> entity = new HttpEntity<>(order,http);
		return entity;
	}

	private HttpEntity<String> getEntity() {
		HttpHeaders http = new HttpHeaders();
		String token = null;
		if (jwtService.getJwt() != null)
			token = jwtService.getJwt();
		http.add("Authorization", "Bearer " + token);
		HttpEntity<String> entity = new HttpEntity<String>(http);
		return entity;
	}

}
