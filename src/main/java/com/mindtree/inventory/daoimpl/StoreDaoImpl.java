package com.mindtree.inventory.daoimpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mindtree.inventory.dao.StoreDao;
import com.mindtree.inventory.entity.Store;

@Repository
@SuppressWarnings("unchecked")
public class StoreDaoImpl implements StoreDao {

	@Autowired
	private SessionFactory factory;
	
	@Override
	public List<Store> getInventoryRecords() {
		Session session = factory.openSession();
		
		List<Store> listOfInventoryRecords = session.createCriteria(Store.class).list();
		session.close();		
		return listOfInventoryRecords;
	}


	@Override
	
	public boolean deleteInventoryRecord(Store s) {
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
	public boolean updateInventoryRecord(Store s) {
		
		boolean isSuccessful = false;
		Session session = factory.openSession();
		try{
			Transaction tx = session.beginTransaction();
			session.update(s);
			tx.commit();
			isSuccessful = true;
		}catch(HibernateException e){
			System.out.println("Error Occured during update.");			
		}finally{			
			session.close();	
		}
			
		return isSuccessful;
	}


	@Override
	public boolean saveInventoryRecord(Store s) {
		
		boolean isSuccessful = false;
		Session session = factory.openSession();
		try{
			Transaction tx = session.beginTransaction();
			session.save(s);
			tx.commit();
			isSuccessful = true;
		}catch(HibernateException e){
			e.printStackTrace();
			System.out.println("Error Occured during insert.");			
		}finally{			
			session.close();	
		}			
		return isSuccessful;
	}

}
