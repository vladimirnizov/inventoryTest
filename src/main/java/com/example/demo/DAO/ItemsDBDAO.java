package com.example.demo.DAO;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.entity.Inventory;
import com.example.demo.repo.InventoryRepo;

@Component
public class ItemsDBDAO implements ItemsDAO {
	
	@Autowired
	private InventoryRepo invRepo;
	

	@Override
	public void save(Inventory inv) {
		invRepo.save(inv);
		
	}

	@Override
	public void delete(Inventory inv) {
		invRepo.delete(inv);
		
	}

	@Override
	public Inventory getItem(int number) {
		return invRepo.findOne(number);
		
	}

	@Override
	public ArrayList<Inventory> getAllInventory() {
		return (ArrayList<Inventory>) invRepo.findAll();
	}

	@Override
	public boolean ifExist(String name) {
		if(invRepo.findByName(name)!=null)
			return true;
		
		return false;
	}



}
