package com.mindtree.inventory.dao;

import java.util.List;

import com.mindtree.inventory.entity.PendingStore;

public interface PendingStoreDao {
	public List<PendingStore> getPendingInventoryRecords();
	public boolean savePendingInventoryRecord(PendingStore s);
	public boolean deletePendingInventoryRecord(PendingStore s);
	public boolean updatePendingInventoryRecord(PendingStore s);

}
