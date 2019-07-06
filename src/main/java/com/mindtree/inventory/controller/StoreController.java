package com.mindtree.inventory.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.inventory.entity.PendingStore;
import com.mindtree.inventory.entity.Store;
import com.mindtree.inventory.service.PendingStoreService;
import com.mindtree.inventory.service.StoreService;

@RestController
public class StoreController {
	
	@Autowired
	private StoreService storeService;
	
	@Autowired
	private PendingStoreService pendingStoreService;
			
	@RequestMapping(value="/getInventory", method=RequestMethod.GET)
	public List<Store> getInventoryRecords()
	{
		return storeService.getInventoryRecords();
	}
	
	@RequestMapping(value="/deleteInventory", method=RequestMethod.DELETE )
	public ResponseEntity<String> deleteInventoryRecords(@RequestBody Store s, HttpServletRequest request)
	{
		if((request.getSession().getAttribute("user_role") != null))
		{
			if(request.getSession().getAttribute("user_role").equals("Store Manager"))
				if(storeService.deleteInventoryRecord(s))
					return new ResponseEntity<>("Successfully Deleted the record",HttpStatus.OK);
			if(request.getSession().getAttribute("user_role").equals("Department Manager"))
				if(pendingStoreService.deletePendingInventoryRecord(new PendingStore(s), "Save"))
					return new ResponseEntity<>("Successfully Deleted the record",HttpStatus.OK);
			
			return new ResponseEntity<>("Unable to delete the record. Try again",HttpStatus.NOT_FOUND);
					
		}			
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);				
	}
	
	@RequestMapping(value="/updateInventory", method=RequestMethod.PUT )
	public ResponseEntity<String> updateInventoryRecords(@RequestBody Store s, HttpServletRequest request)
	{
		if((request.getSession().getAttribute("user_role") != null))
		{
			if(request.getSession().getAttribute("user_role").equals("Store Manager"))
				if(storeService.updateInventoryRecord(s))
					return new ResponseEntity<>("Successfully Updated the record",HttpStatus.OK);
			if(request.getSession().getAttribute("user_role").equals("Department Manager"))
				if(pendingStoreService.updatePendingInventoryRecord(new PendingStore(s)))
					return new ResponseEntity<>("Successfully Updated the record",HttpStatus.OK);
			
			return new ResponseEntity<>("Unable to update the record. Try again",HttpStatus.NOT_FOUND);
					
		}			
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);				
		
	}
	
	@RequestMapping(value="/addInventory", method=RequestMethod.POST )
	public ResponseEntity<String> addInventoryRecords(@RequestBody Store s, HttpServletRequest request)
	{	
		if((request.getSession().getAttribute("user_role") != null))
		{
			if(request.getSession().getAttribute("user_role").equals("Store Manager"))
				if(storeService.addInventoryRecord(s))
					return new ResponseEntity<>("Successfully inserted the record",HttpStatus.OK);
			if(request.getSession().getAttribute("user_role").equals("Department Manager"))
				if(pendingStoreService.addPendingInventoryRecord(new PendingStore(s)))
					return new ResponseEntity<>("Successfully inserted the record",HttpStatus.OK);
			
			return new ResponseEntity<>("Unable to insert the record. Try again",HttpStatus.BAD_REQUEST);
					
		}			
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);		
	}


}
