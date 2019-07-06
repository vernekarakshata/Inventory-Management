package com.mindtree.inventory.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;

@Entity
@Table(name="users")
public class User {	
	@Id
	private String username;
	private String password;
	private String email;
	
	@ManyToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
    @JoinTable(
        name = "userrole", 
        joinColumns = { @JoinColumn(name = "username") }, 
        inverseJoinColumns = { @JoinColumn(name = "roleid") }
    )
	
    Set<Role> roles = new HashSet<Role>();	
	
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
	
	public String getEmailId() {
		return email;
	}
	
	public void setEmailId(String email) {
		this.email = email;
	}
	
	public Set<Role> getRoles() {
		return roles;
	}
	
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
}
