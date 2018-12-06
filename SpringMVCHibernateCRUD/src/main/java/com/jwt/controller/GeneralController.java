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
 * This class controls the general redirection from the site
 * */

@Controller
@RequestMapping("/user")
public class GeneralController{

	@Autowired
	private UserService userService;
	
	private static final Logger log = Logger
			.getLogger(GeneralController.class);
	
	// TIMER redirection
	@RequestMapping(value = "/timer")
	public ModelAndView goToSCT1(HttpSession session) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("timeCountDown", 60);	//60
		mv.setViewName("timer");
	    return mv;
	}
	
	// HOME redirection - will depend if student or teacher
	@RequestMapping(value = {"/homeTeacher", "/homeStudent"})
	public ModelAndView goToHome(HttpSession session) {
		try {
			assert session.getAttribute("username").toString() instanceof String : "username not in cookie";
			assert session.getAttribute("password").toString() instanceof String : "password not in cookie";
			
			log.info("s1 " + session.getAttribute("username").toString());
			log.info("s2 " + session.getAttribute("password").toString());
		} catch (Exception e) {
			return new ModelAndView("redirect:/");
		}
		// if all the info is in the cookie -------------

		ModelAndView mv = new ModelAndView();
		User u = userService.getUserByNameAndPassword(session.getAttribute("username").toString(),
				session.getAttribute("password").toString());

		mv.addObject("output", "Welcome " + u.getName());
		mv.addObject("type", u.getType());
		mv.addObject("userRealName", u.getRealname());

		if (u.getType().equals("student"))		mv.setViewName("homeStudent");
		else									mv.setViewName("homeTeacher");
		
		return mv;
	}
	
	@RequestMapping(value = "/exitTicketTeacher")
	public ModelAndView goExitTicketTeacher(HttpSession session) {
		log.info("entro en Exit Ticket Teacher");
		log.info("session "+session.getId());
	    
	    return new ModelAndView("redirect:/user/exitTicketTeacher?showETList=showETList");
	}	
	
	@RequestMapping(value = "/attendanceTeacher")
	public ModelAndView goAttendanceTeacher(HttpSession session) {
		log.info("entro en Attendance Teacher");
		log.info("session "+session.getId());
	    
	    return new ModelAndView("redirect:/user/attendanceTeacher?showCDList=showCDList");
	}	
	
	//logout
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
		return "redirect:/";
	}
}