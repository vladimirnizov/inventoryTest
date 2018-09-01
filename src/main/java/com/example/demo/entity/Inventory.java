package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.example.demo.enums.ItemType;

@Entity(name = "INVENTORY")
public class Inventory {

	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int number;

	@Column (name = "type")
	@Enumerated(EnumType.STRING)
	private ItemType item;
	
	@Column (name = "name")
	private String name;
	
	@Column (name = "amount")
	private int amount;
	
	@Column (name = "code")
	private String inventoryCode;
	
	public Inventory() { }

	public Inventory(ItemType item, String name, int amount, String inventoryCode) {
		super();
		this.item = item;
		this.name = name;
		this.amount = amount;
		this.inventoryCode = inventoryCode;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public ItemType getItem() {
		return item;
	}

	public void setItem(ItemType item) {
		this.item = item;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getInventoryCode() {
		return inventoryCode;
	}

	public void setInventoryCode(String inventoryCode) {
		this.inventoryCode = inventoryCode;
	}
	
	
	@Override
	public String toString() {
		return "Inventory [number=" + number + ", item=" + item + ", name=" + name + ", amount=" + amount
				+ ", inventoryCode=" + inventoryCode + "]";
	}
}
