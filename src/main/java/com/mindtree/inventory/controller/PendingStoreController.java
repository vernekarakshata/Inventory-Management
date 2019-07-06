package com.mindtree.inventory.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.mindtree.inventory.entity.PendingStore;
import com.mindtree.inventory.service.PendingStoreService;

@RestController
public class PendingStoreController {
	
	@Autowired
	private PendingStoreService pendingStoreService;
	
	@RequestMapping(value="/getApprovalInventory", method=RequestMethod.GET)
	public List<PendingStore> getInventoryRecords()
	{
		List<PendingStore> pendingStoreRecords = pendingStoreService.getPendingInventoryRecords();
		Collections.sort(pendingStoreRecords);
		return pendingStoreRecords;
	}
		
	@RequestMapping(value="/rejectApprovalInventory", method=RequestMethod.DELETE )
	public ResponseEntity<String> rejectApprovalInventoryRecords(@RequestBody PendingStore s)
	{		
		if(pendingStoreService.deletePendingInventoryRecord(s, "Delete"))
			return new ResponseEntity<>("The request has been deleted successfully.",HttpStatus.OK);
		else
			return new ResponseEntity<>("Unable to reject the request. Try again later.",HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value="/approveApprovalInventory", method=RequestMethod.POST )
	public ResponseEntity<String> approveApprovalInventoryRecords(@RequestBody PendingStore s)
	{
		if(pendingStoreService.approvePendingInventoryRecord(s))
			return new ResponseEntity<>("The request is approved sucessfully!",HttpStatus.OK);
		else
			return new ResponseEntity<>("Unable to accept the request. Try again later.",HttpStatus.NOT_FOUND);
	}

}
