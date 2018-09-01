package com.example.demo.facades;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.DAO.ItemsDBDAO;
import com.example.demo.entity.Inventory;
import com.example.demo.exceptions.AlreadyExistException;
import com.example.demo.exceptions.NotExistException;
import com.example.demo.exceptions.wrongQuantityException;

@Component
public class InventoryFacade {

	@Autowired
	ItemsDBDAO itemDAO;
	
	public synchronized void addItem(Inventory inv) {
		if(itemDAO.ifExist(inv.getName())) 
		{
			throw new AlreadyExistException(inv.getName() + " already exist!");
		}
		else
		{
			itemDAO.save(inv);
		}
	}
	
	public synchronized void deleteItem(Inventory inv) {
		if(itemDAO.ifExist(inv.getName())) 
		{
			itemDAO.delete(inv);
		}
		else
		{
			throw new NotExistException(inv.getName() + " does not exist!");
		}
	}
	
	public ArrayList<Inventory> getAll(){
		return itemDAO.getAllInventory();
	}
	
	public Inventory getItem(int number) {
		return itemDAO.getItem(number);
	}
	
	public synchronized void withdrawQuantityItems(int itemID, int quantity) {
		Inventory thisItem = itemDAO.getItem(itemID);
		int itemsAmount = thisItem.getAmount();

		if(quantity>0 && thisItem!=null && itemsAmount>=quantity) {
			
			thisItem.setAmount(itemsAmount-quantity);
			itemDAO.save(thisItem);
		}
		else {
			if(thisItem==null) {
				throw new NotExistException("Item with id number "+itemID+" does not exist!");

			}
			
			if(quantity<=0 || itemsAmount<quantity) {
				throw new wrongQuantityException("quantity should be less than "+itemsAmount+" and bigger than 0");

			}
			
			
		}
	}
	
	public synchronized void depositQuantityItems(int itemID, int quantity) {
		Inventory thisItem = itemDAO.getItem(itemID);
		int itemsAmount = thisItem.getAmount();

		if(quantity>0 && thisItem!=null) {
			
			thisItem.setAmount(itemsAmount+quantity);
			itemDAO.save(thisItem);
		}
		else {
			if(thisItem==null) {
				throw new NotExistException("Item with id number "+itemID+" does not exist!");

			}
			
			if(quantity<=0) {
				throw new wrongQuantityException("quantity should be and bigger than 0");

			}
		}
	}
	
	
}
