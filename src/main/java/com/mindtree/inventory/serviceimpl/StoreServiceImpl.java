package com.mindtree.inventory.serviceimpl;

import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mindtree.inventory.dao.PendingStoreDao;
import com.mindtree.inventory.dao.StoreDao;
import com.mindtree.inventory.entity.PendingStore;
import com.mindtree.inventory.entity.Store;
import com.mindtree.inventory.service.StoreService;

@Service
public class StoreServiceImpl implements StoreService{

	@Autowired
	private StoreDao storeDao;
	
	@Autowired
	private PendingStoreDao pendingStoreDao;
	
	@Override
	public List<Store> getInventoryRecords() {
		List<PendingStore> pendingRecords = pendingStoreDao.getPendingInventoryRecords();	
		List<Store> approvedRecords = storeDao.getInventoryRecords();
		
		for(int i=0; i<pendingRecords.size(); i++)
		{
			PendingStore p = pendingRecords.get(i);
			for(int j=0; j<approvedRecords.size(); j++)
			{
				if(p != null && (p.getProductid() == approvedRecords.get(j).getProductid()))
				{
					approvedRecords.remove(j);
					break;
				}
			}						
			approvedRecords.add(new Store(p));
		}			
		
		Collections.sort(approvedRecords);
		return approvedRecords;
	}

	@Override
	public boolean addInventoryRecord(Store s) {
		s.setStatus("Approved");
		return storeDao.saveInventoryRecord(s);
	}

	@Override
	public boolean deleteInventoryRecord(Store s) {
		if(s.getStatus().equals("Approved"))
			return storeDao.deleteInventoryRecord(s);
		else
		{
			if(pendingStoreDao.deletePendingInventoryRecord(new PendingStore(s)))
			{
				s.setStatus("Approved");
				return storeDao.deleteInventoryRecord(s);
			}			
		}
		
		return false;
	}

	@Override
	public boolean updateInventoryRecord(Store s) {
		if(s.getStatus().equals("Approved"))
			return storeDao.updateInventoryRecord(s);
		else
		{
			if(pendingStoreDao.deletePendingInventoryRecord(new PendingStore(s)))
			{
				s.setStatus("Approved");
				return storeDao.updateInventoryRecord(s);
			}			
		}		
		return false;
	}
}
