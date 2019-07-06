package com.mindtree.inventory.service;

import java.util.List;
import com.mindtree.inventory.entity.PendingStore;


public interface PendingStoreService {
	
	public List<PendingStore> getPendingInventoryRecords();
	public boolean addPendingInventoryRecord(PendingStore s);
	public boolean deletePendingInventoryRecord(PendingStore s, String type);
	public boolean updatePendingInventoryRecord(PendingStore s);
	public boolean approvePendingInventoryRecord(PendingStore s);

}
