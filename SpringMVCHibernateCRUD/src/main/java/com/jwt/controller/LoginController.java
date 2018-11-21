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
	public ModelAndView validateUsr(@RequestParam("username")String username,@RequestParam("password")String password, HttpSession session) {
		String msg = "";
		String type="";
		boolean isValid = userService.findUser(username, password);

		ModelAndView mv=new ModelAndView();
		User u=null;
		if(isValid) {
			msg = "Welcome " + username + "!";
			u=userService.getUserByNameAndPassword(username, password);
			type=u.getType();
			
			log.info("What type of user?= " + type);
			//return new ModelAndView("result", "output", msg);	//output is the attribute
			
			
			if(type.equals("student"))
				mv.setViewName("homeStudent");
			else if (type.equals("teacher"))
				mv.setViewName("homeTeacher");
			
			mv.addObject("type", type);
			mv.addObject("userRealName", u.getRealname());
			
			//session Attributes
			session.setAttribute("username", username);
			session.setAttribute("password", password);
			session.setAttribute("userId", u.getId());
			log.info("sessionID="+session.getId());
			
		} else {
			log.info("invalid credentials");
			msg = "Invalid credentials";
			type="none";
			//mv.setViewName("errorPage");
			return new ModelAndView("redirect:/");
		}
		
		
		mv.addObject("output", msg);
		return mv;
	}
}