package com.example.demo.services;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Inventory;
import com.example.demo.exceptions.AlreadyExistException;
import com.example.demo.exceptions.NotExistException;
import com.example.demo.exceptions.wrongQuantityException;
import com.example.demo.facades.InventoryFacade;

@RestController
@RequestMapping(value = "inventory")
public class RestService {

	@Autowired
	InventoryFacade facade;
	
	@RequestMapping(value = "/getItem/{number}" , method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<Inventory> getItem(@PathVariable("number") int number ,HttpServletRequest request , HttpServletResponse response)
	{

		Inventory inv = facade.getItem(number);
		if(inv!=null)
		  return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(inv); 
		else
		  return ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.TEXT_PLAIN).body(null);	
		
	}
	
	@RequestMapping(value = "getAll" , method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<List<Inventory>> getAll(HttpServletRequest request , HttpServletResponse response)
	{

		List<Inventory> inventory = facade.getAll();
		if(inventory!=null)
		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(inventory);
		else
		return ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.TEXT_PLAIN).body(null);	
		
	}
	
	@RequestMapping(value = "/addItem" , method = RequestMethod.POST)
	public ResponseEntity<Inventory> addItem(@RequestBody Inventory inv , HttpServletRequest request , HttpServletResponse response)
	{
		try {
			facade.addItem(inv);
			return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(inv);
		} catch (AlreadyExistException e) {
			System.out.println(e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).contentType(MediaType.TEXT_PLAIN).body(null);
		}
	}
	
	@RequestMapping(value = "/removeItem/{number}" , method = RequestMethod.DELETE)
	public ResponseEntity<String> removeItem(@RequestBody @PathVariable("number") int number , HttpServletRequest request , HttpServletResponse response)
	{

		try{
		facade.deleteItem(facade.getItem(number));
		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.TEXT_PLAIN).body("Item was removed from the data base");
		}catch( NotExistException e){
		return ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.TEXT_PLAIN).body(e.getMessage());	
		}
	}
	
	
	@RequestMapping(value = "/withdrawItems/{number}/{quant}" , method = RequestMethod.PUT)
	public ResponseEntity<Inventory> withdrawItems(@RequestBody @PathVariable("number") int number, 
			@PathVariable("quant") int quant , HttpServletRequest request , HttpServletResponse response)
	{
		try
		{
		facade.withdrawQuantityItems(number, quant);
		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(facade.getItem(number));
		
		}
		catch(NotExistException e){
		    return ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.APPLICATION_JSON).body(null);
		}
		catch(wrongQuantityException e){
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).contentType(MediaType.TEXT_PLAIN).body(null);
		}
	}
	
	
	@RequestMapping(value = "/depositItems/{number}/{quant}" , method = RequestMethod.PUT)
	public ResponseEntity<Inventory> depositItems(@RequestBody @PathVariable("number") int number, 
			@PathVariable("quant") int quant , HttpServletRequest request , HttpServletResponse response)
	{
		try
		{
		facade.depositQuantityItems(number, quant);
		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(facade.getItem(number));
		
		}
		catch(NotExistException e){
		    return ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.APPLICATION_JSON).body(null);
		}
		catch(wrongQuantityException e){
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).contentType(MediaType.TEXT_PLAIN).body(null);
		}
	}
	
}
