package com.mindtree.inventory.serviceimpl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mindtree.inventory.dao.PendingStoreDao;
import com.mindtree.inventory.dao.StoreDao;
import com.mindtree.inventory.entity.PendingStore;
import com.mindtree.inventory.entity.Store;
import com.mindtree.inventory.service.PendingStoreService;

@Service
public class PendingStoreServiceImpl implements PendingStoreService {
	
	@Autowired
	private PendingStoreDao pendingStoreDao;
	
	@Autowired
	private StoreDao storeDao;

	@Override
	public List<PendingStore> getPendingInventoryRecords() {
		
		
		return pendingStoreDao.getPendingInventoryRecords();
	}

	@Override
	public boolean addPendingInventoryRecord(PendingStore s) {
		s.setStatus("Pending for insert");
		return pendingStoreDao.savePendingInventoryRecord(s);
	}

	@Override
	public boolean deletePendingInventoryRecord(PendingStore s, String type) {
		if(type.equals("Save"))
		{
			s.setStatus("Pending for delete");
			return pendingStoreDao.updatePendingInventoryRecord(s);
		}
		s.setStatus("Pending for delete");
		return pendingStoreDao.deletePendingInventoryRecord(s);
	}

	@Override
	public boolean updatePendingInventoryRecord(PendingStore s) {
		s.setStatus("Pending for update");
		return pendingStoreDao.updatePendingInventoryRecord(s);
	}

	@Override
	public boolean approvePendingInventoryRecord(PendingStore p) {
		
		boolean isSuccessful = false;
		
		Store store = new Store(p);		
		store.setStatus("Approved");		
		
		System.out.println(p.getStatus());
		
		if(p.getStatus().contains("insert"))
		{
			if(storeDao.saveInventoryRecord(store))
				isSuccessful = pendingStoreDao.deletePendingInventoryRecord(p);
		}
		else if(p.getStatus().contains("delete"))
		{
			if(storeDao.deleteInventoryRecord(store))
				isSuccessful = pendingStoreDao.deletePendingInventoryRecord(p);
		}
		else if(p.getStatus().contains("update"))
		{
			if(storeDao.updateInventoryRecord(store))
				isSuccessful = pendingStoreDao.deletePendingInventoryRecord(p);
		}				
		return isSuccessful;
	}

}
