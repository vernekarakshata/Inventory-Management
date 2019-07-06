package com.mindtree.inventory.dao;

import java.util.List;

import com.mindtree.inventory.entity.User;

public interface LoginDao {
	public List<User> getUserDetails();
	public boolean isUserPresent(String username, String password);
	public User getUserDetails(String username);
}
