package com.example.demo.DAO;

import java.util.ArrayList;

import com.example.demo.entity.Inventory;

public interface ItemsDAO {

	
	void save(Inventory inv);
	
	void delete(Inventory inv);

	Inventory getItem(int number);

	ArrayList<Inventory> getAllInventory();
	
	boolean ifExist(String name);
}
