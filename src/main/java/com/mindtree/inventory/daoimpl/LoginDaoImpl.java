package com.mindtree.inventory.daoimpl;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.mindtree.inventory.dao.LoginDao;
import com.mindtree.inventory.entity.User;

@Repository
@SuppressWarnings("unchecked")
public class LoginDaoImpl implements LoginDao {
	
	@Autowired
	private SessionFactory factory;

	@Override
	public List<User> getUserDetails() {
		Session session = factory.openSession();
		List<User> list = session.createCriteria(User.class).list();
		session.close();				
		return list;
	}

	@Override
	public boolean isUserPresent(String username, String password) {
		List<User> listOfUsers = getUserDetails();
		for(int i=0; i<listOfUsers.size(); i++)
		{
			User u = listOfUsers.get(i);
			if(u.getUsername().equals(username) && u.getPassword().equals(password))
				return true;
		}
		return false;
	}

	@Override
	public User getUserDetails(String username) {
		User u;
		Session session = factory.openSession();
		u = (User) session.get(User.class, username);
		session.close();
		return u;
	}

}
