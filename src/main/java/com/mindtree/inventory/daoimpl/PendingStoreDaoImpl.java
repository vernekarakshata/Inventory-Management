package com.mindtree.inventory.daoimpl;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.mindtree.inventory.dao.PendingStoreDao;
import com.mindtree.inventory.entity.PendingStore;


@Repository
@SuppressWarnings("unchecked")
public class PendingStoreDaoImpl implements PendingStoreDao {
	
	@Autowired
	private SessionFactory factory;

	@Override
	public List<PendingStore> getPendingInventoryRecords() {		
		Session session = factory.openSession();
		List<PendingStore> listOfPendingInventoryRecords = session.createCriteria(PendingStore.class).list();
		session.close();		
		return listOfPendingInventoryRecords;
	}

	@Override
	public boolean savePendingInventoryRecord(PendingStore s) {
		boolean isSuccessful = false;
		Session session = factory.openSession();
		try{
			Transaction tx = session.beginTransaction();
			session.save(s);
			tx.commit();
			isSuccessful = true;
		}catch(HibernateException e){
			System.out.println("Error Occured during insert.");			
		}finally{			
			session.close();	
		}			
		return isSuccessful;
	}
	

	@Override
	public boolean deletePendingInventoryRecord(PendingStore s) {
		boolean isSuccessful = false;
		Session session = factory.openSession();
		try{
			Transaction tx = session.beginTransaction();
			session.delete(s);
			tx.commit();
			isSuccessful = true;
		}catch(HibernateException e){
			System.out.println("Error Occured during delete.");			
		}finally{
			session.close();	
		}
			
		return isSuccessful;
	}

	@Override
	public boolean updatePendingInventoryRecord(PendingStore s) {

		boolean isSuccessful = false;
		Session session = factory.openSession();
		try{
			Transaction tx = session.beginTransaction();
			session.saveOrUpdate(s);
			tx.commit();
			isSuccessful = true;
		}catch(HibernateException e){
			e.printStackTrace();
			System.out.println("Error Occured during update");			
		}finally{
			session.close();	
		}			
		return isSuccessful;
	}

	

}
