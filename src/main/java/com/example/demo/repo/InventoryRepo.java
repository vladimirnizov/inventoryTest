package com.example.demo.repo;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.Inventory;

public interface InventoryRepo extends CrudRepository<Inventory, Integer>{

	
//
//	@Query("DELETE FROM INVENTORY I WHERE I.number = :number")
//	void removeItem(@Param("number") int number);
//	
	@Query("SELECT I FROM INVENTORY I WHERE I.number = :number")
	Inventory findOne(@Param("number")int number);
	
	@Query("SELECT I FROM INVENTORY I WHERE I.name = :name")
	Inventory findByName(@Param("name")String name);
	
}
