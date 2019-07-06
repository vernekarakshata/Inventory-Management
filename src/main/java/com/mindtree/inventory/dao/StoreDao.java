package com.mindtree.inventory.dao;

import java.util.List;

import com.mindtree.inventory.entity.Store;

public interface StoreDao {
	
	public List<Store> getInventoryRecords();
	public boolean saveInventoryRecord(Store s);
	public boolean deleteInventoryRecord(Store s);
	public boolean updateInventoryRecord(Store s);

}
