package com.mindtree.inventory.dto;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="users")
public class UserLogin {
	
	@Id
	String username;
	String password;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
