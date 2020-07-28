package com.akash.truyum.dao;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.akash.truyum.model.Menu;

@Service
public interface MenuDAOService {

	public List<Menu> readAllCsvData() throws IOException;;

	public Menu findDataById(Integer id) throws Exception;

	public Menu writeToCSV(Menu menu) throws IOException;

}
