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
	
	// HOME redirection
	@RequestMapping(value = {"/homeTeacher", "/homeStudent"})
	public ModelAndView goToSCTX(HttpSession session) {
		try {
			log.info("s1 " + session.getAttribute("username").toString());
			log.info("s2 " + session.getAttribute("password").toString());
		} catch (Exception e) {
			return new ModelAndView("redirect:/");
		}
		// if all the info is in the cookie

		ModelAndView mv = new ModelAndView();
		User u = userService.getUserByNameAndPassword(session.getAttribute("username").toString(),
				session.getAttribute("password").toString());

		mv.addObject("output", "Welcome " + u.getName());
		mv.addObject("type", u.getType());
		mv.addObject("userRealName", u.getRealname());

		if (u.getType().equals("student"))
			mv.setViewName("homeStudent");
		else	
			mv.setViewName("homeTeacher");
		return mv;
	}
		
	/*
	@RequestMapping(value = "/exitTicketS")
	public ModelAndView goToSCT2(HttpSession session) {
		log.info("entro en Exit Ticket Student");
		log.info("session "+session.getId());
		List<ExitTicketEntry> etList = getList();
		ModelAndView mv=new ModelAndView();
		mv.setViewName("exitTicketS");
    	mv.addObject("etList", etList);
	    return mv;
	}*/
	
	
	
	@RequestMapping(value = "/exitTicketTeacher")
	public String goToSCT3(HttpSession session) {
		log.info("entro en Exit Ticket Teacher");
		log.info("session "+session.getId());
	    return "exitTicketTeacher";
	}	
	
	/*
	
	
	
	private List<ExitTicketEntry> getList(){
	    try {
		    List<ExitTicketEntry> listET=exitTicketService.listExitTickets();
		    log.info("Im out "+listET.size());
		    return listET;
	    }catch(Exception e) {
		    log.error("Listing unsuccesful");
	    	return null;
	    }
	}*/
	
	//logout
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
		return "redirect:/";
	}
}