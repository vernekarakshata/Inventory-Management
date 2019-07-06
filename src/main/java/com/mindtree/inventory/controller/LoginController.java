package com.mindtree.inventory.controller;

import java.net.URI;
import java.net.URISyntaxException;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import com.mindtree.inventory.dto.UserLogin;
import com.mindtree.inventory.service.LoginService;

@RestController
public class LoginController {
	@Autowired
	private LoginService loginService;
	
	@RequestMapping(value="/checkuserexist", method=RequestMethod.POST)
	public ResponseEntity<String> loginUser(@RequestBody UserLogin u, HttpSession session)
	{
		if(loginService.isUserPresent(u))
		{
			if(loginService.getRoleType(u.getUsername()).equals("Store Manager"))
				session.setAttribute("user_role", "Store Manager");
			else
				session.setAttribute("user_role", "Department Manager");
			
			session.setAttribute("user_name", u.getUsername());
			HttpHeaders responseHeaders = new HttpHeaders();
			URI location = null;
			try {
				location = new URI("modify");
			} catch (URISyntaxException e) {
				
				e.printStackTrace();
			}
			responseHeaders.setLocation(location);
			return new ResponseEntity<>("Successfull login",responseHeaders,HttpStatus.OK);			
		}
		else		
			return new ResponseEntity<>("Username or Password incorrect!",HttpStatus.UNAUTHORIZED);
	}
	
	@RequestMapping(value="/modify", method=RequestMethod.GET)
	public ModelAndView loginStoreManagemente()
	{
		ModelAndView mv = new ModelAndView("modifystore");
		return mv;	
	}
	
	@RequestMapping(value="/approve", method=RequestMethod.GET)
	public ModelAndView approve()
	{
		ModelAndView mv = new ModelAndView("approvestore");
		return mv;		
	}
	
	

}
