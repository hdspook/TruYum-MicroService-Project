package com.akash.truyum.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.akash.truyum.dao.MenuDAOService;
import com.akash.truyum.model.Menu;

@RestController
@RequestMapping("/menu")
public class MenuController {

	@Autowired
	MenuDAOService service;

	@GetMapping("/find")
	public List<Menu> getAllMenuItems() throws IOException {

		return service.readAllCsvData();
	}

	@GetMapping("/find/{id}")
	public Menu getMenuById(@PathVariable("id") Integer id) throws Exception {
		return service.findDataById(id);
	}

	@PostMapping("/add")
	public Menu addToCsv(Menu menu) throws IOException {
		return service.writeToCSV(menu);
	}

	
	
}
