package com.jwt.controller;

import javax.servlet.http.HttpSession;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.jwt.model.User;
import com.jwt.service.UserService;

@Controller
@RequestMapping("/user")
public class LoginController {

	private static final Logger log = Logger
			.getLogger(LoginController.class);
	
	@Autowired
	private UserService userService;
	
	// Checks if the user credentials are valid or not.
	@RequestMapping(value = "/validate", method = RequestMethod.POST)
	public ModelAndView validateUser(@RequestParam("username")String username,@RequestParam("password")String password, HttpSession session) {
		String msg = "";
		String type="";

		ModelAndView mv=new ModelAndView();
		User u=null;
		
		//if there is a user with that user name and password
		if(userService.findUser(username, password)) {
			msg = "Welcome " + username + "!";
			u=userService.getUserByNameAndPassword(username, password);
			type=u.getType();
			
			if(type.equals("student"))
				mv.setViewName("homeStudent");
			else if (type.equals("teacher"))
				mv.setViewName("homeTeacher");
			
			mv.addObject("type", type);
			mv.addObject("userRealName", u.getRealname());
			
			//session Attributes - saving the attributes to the cookie
			session.setAttribute("username", username);
			session.setAttribute("password", password);
			session.setAttribute("userId", u.getId());
			
		} else {
			log.info("invalid credentials");
			msg = "Invalid credentials";
			type="none";
			return new ModelAndView("redirect:/");
		}
		
		mv.addObject("output", msg);
		return mv;
	}
}