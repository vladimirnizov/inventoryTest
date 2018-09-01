package com.example.demo;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.DAO.ItemsDBDAO;
import com.example.demo.entity.Inventory;
import com.example.demo.enums.ItemType;
import com.example.demo.facades.InventoryFacade;
import com.example.demo.repo.InventoryRepo;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InventoryTestApplicationTests {

	@Autowired
	InventoryFacade facade;
	
	@Autowired
	InventoryRepo rep;
	
	@Autowired
	ItemsDBDAO dbdao= new ItemsDBDAO();
	
	@Test
	public void contextLoads() {
	}

	
	@Test
	public void createItem()
	{
		
		facade.addItem(new Inventory(ItemType.KNIFE, "test3", 10, "test3"));
		Assert.assertNotNull(rep.findByName("test3"));
	}
	
//	@Test
//	public void createItem()
//	{
//		dbdao.save(new Inventory(ItemType.KNIFE, "test2", 10, "test2"));
//		Assert.assertNotNull(rep.findByName("test2"));
//	}
}
