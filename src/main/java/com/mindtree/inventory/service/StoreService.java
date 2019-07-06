package com.mindtree.inventory.service;

import java.util.List;
import com.mindtree.inventory.entity.Store;

public interface StoreService {
	public List<Store> getInventoryRecords();
	public boolean addInventoryRecord(Store s);
	public boolean deleteInventoryRecord(Store s);
	public boolean updateInventoryRecord(Store s);
}
