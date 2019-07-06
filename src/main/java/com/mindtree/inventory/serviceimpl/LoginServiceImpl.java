package com.mindtree.inventory.serviceimpl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindtree.inventory.dao.LoginDao;
import com.mindtree.inventory.dto.UserLogin;
import com.mindtree.inventory.entity.Role;
import com.mindtree.inventory.entity.User;
import com.mindtree.inventory.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService {
	
	@Autowired
	private LoginDao loginDao;

	@Override
	public boolean isUserPresent(UserLogin u) {		
		return loginDao.isUserPresent(u.getUsername(), u.getPassword());
	}

	@Override
	public String getRoleType(String username) {
		User u = loginDao.getUserDetails(username);
		Set<Role> roles = u.getRoles();
		
		if(roles.size() == 2)
			return "Store Manager";		
		
		return roles.iterator().next().getRolename();
	}
	
	

}
