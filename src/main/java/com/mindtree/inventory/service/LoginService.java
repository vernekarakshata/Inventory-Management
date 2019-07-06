package com.mindtree.inventory.service;

import com.mindtree.inventory.dto.UserLogin;

public interface LoginService {
	
	public boolean isUserPresent(UserLogin u);
	public String getRoleType(String username);
}
