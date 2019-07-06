package com.mindtree.inventory.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class HomeController {
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public ModelAndView loginUser()
	{
		ModelAndView mv = new ModelAndView("login");
		return mv;		
	}
			
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public ModelAndView logoutUser(HttpServletRequest request, SessionStatus status)
	{		
		HttpSession session = request.getSession(false);
	    if( session != null ) 
	    	session.invalidate();
	   	
	    ModelAndView mv = new ModelAndView("logout");
		return mv;		
	}
}
