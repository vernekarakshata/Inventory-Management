package com.mindtree.inventory.configuration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import com.mindtree.inventory.controller.HomeController;


public class SessionValidator extends HandlerInterceptorAdapter{
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
	{		
		HttpSession session = request.getSession();				
		RequestMapping rm = ((HandlerMethod) handler).getMethodAnnotation(RequestMapping.class); 
		 
		if((((HandlerMethod) handler).getBean() instanceof HomeController))
			return true;
		
		if(rm.value()[0].equals("/checkuserexist"))
			return true;
			
		if(session == null || session.getAttribute("user_name") == null 
				|| session.getAttribute("user_role") == null)
			return false;
		
		if(session.getAttribute("user_role").equals("Department Manager") && rm.value()[0].equals("/approve"))
			return false;			
		
		return true;		
	}
}