package com.jwt.controller;

import java.io.IOException;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.jwt.model.ExitTicket;
import com.jwt.model.User;
import com.jwt.model.UserExitTicket;
import com.jwt.service.UserService;


/*
 * This class includes the home redirection not under /user (which would be the loggedin)
 * */

@Controller
public class HomeController{
	
	private static final Logger log = Logger
			.getLogger(HomeController.class);
	
	@RequestMapping(value = "/")
	public ModelAndView listUser(ModelAndView model) throws IOException {		
		model.setViewName("home");
		return model;
	}
}