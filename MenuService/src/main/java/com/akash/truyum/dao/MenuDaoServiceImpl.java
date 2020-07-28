package com.akash.truyum.dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.akash.truyum.model.Menu;

import lombok.extern.apachecommons.CommonsLog;

@CommonsLog
@Service
public class MenuDaoServiceImpl implements MenuDAOService {

	@Override
	public List<Menu> readAllCsvData() throws IOException {
		final String path = "data.csv";
		List<Menu> allMenus = new ArrayList<>();
		BufferedReader reader;
		log.info("Reading data from CSV file");
		log.info("File is being read");
		reader = new BufferedReader(new FileReader(path));

		String[] temp;
		String line = "";

		while ((line = reader.readLine()) != null) {
			log.info("Reading each line ");
			temp = line.split(",");
			Integer menuId = Integer.parseInt(temp[0]);
			String menuName = temp[1];
			Double price = Double.parseDouble(temp[2]);
			Menu item = new Menu();
			item.setMenuId(menuId);
			item.setMenuItem(menuName);
			item.setPrice(price);
			allMenus.add(item);
		}
		reader.close();
		return allMenus;

	}

	@Override
	public Menu findDataById(Integer id) throws Exception {

		List<Menu> allMenus = readAllCsvData();
		allMenus.forEach(System.out::println);
		Menu menu = allMenus.parallelStream().filter(i -> i.getMenuId() == id).findAny().get();
		if (menu == null) {
			log.warn("NO SUCH ITEM");
			throw new Exception("No menu item with Id" + id);
		}
		return menu;
	}

	public Menu writeToCSV(Menu menu) throws IOException {
		FileWriter writer = new FileWriter("data.csv", true);
		Integer id = menu.getMenuId();
		String name = menu.getMenuItem();
		Double price = menu.getPrice();
		// CSV -> \nid,name,price\n
		writer.write("\n" + String.valueOf(id) + ",");
		writer.write(name + ",");
		writer.write(String.valueOf(price) + "\n");

		writer.close();
		return menu;
	}

}
