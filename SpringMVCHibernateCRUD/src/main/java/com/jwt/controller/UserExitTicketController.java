package com.jwt.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.jwt.model.User;
import com.jwt.model.UserExitTicket;
import com.jwt.model.ExitTicket;
import com.jwt.service.UserService;
import com.jwt.service.UserExitTicketService;
import com.jwt.service.ExitTicketService;

@Controller
@RequestMapping("/user")
public class UserExitTicketController {

	private static final Logger logger = Logger
			.getLogger(UserExitTicketController.class);

	public UserExitTicketController() {
		System.out.println("UserExitTicketController()");
	}

	@Autowired
	private UserExitTicketService userExitTicketService;
	@Autowired
	private UserService userService;
	@Autowired
	private ExitTicketService exitTicketService;

	@RequestMapping(value = "/newUserExitTicket", method = RequestMethod.GET)
	public ModelAndView newUserExitTicket(ModelAndView model) {
		UserExitTicket userExitTicket = new UserExitTicket();
		model.addObject("userExitTicket", userExitTicket);
		model.setViewName("userExitTicketForm");
		getUserAndETLists(model);
		return model;
	}

	@RequestMapping(value = "/saveUserExitTicket", method = RequestMethod.POST)
	public ModelAndView saveUserExitTicket(@ModelAttribute UserExitTicket userExitTicket) {
		// if date incorrect/empty -> selecting today
		if (userExitTicket.getDateAnswer() == "") {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy.MM.dd");
			LocalDate localDate = LocalDate.now();
			userExitTicket.setDateAnswer(dtf.format(localDate));
		}
		
		// if date incorrect/empty -> selecting today
		if (userExitTicket.getExitTicket().getDateET() == "") {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy.MM.dd");
			LocalDate localDate = LocalDate.now();
			userExitTicket.getExitTicket().setDateET(dtf.format(localDate));
		}
		
		
		if (userExitTicket.getId() == 0) { 
			// if exitTicket id is 0 then creating the
			// exitTicket other updating the exitTicket
			userExitTicketService.addUserExitTicket(userExitTicket);
		} else {
			userExitTicketService.updateUserExitTicket(userExitTicket);
		}
		return new ModelAndView("redirect:/user/manageStudents");
	}

	@RequestMapping(value = "/deleteUserExitTicket", method = RequestMethod.GET)
	public ModelAndView deleteUserExitTicket(HttpServletRequest request) {
		int exitTicketId = Integer.parseInt(request.getParameter("id"));
		userExitTicketService.deleteUserExitTicket(exitTicketId);
		return new ModelAndView("redirect:/user/manageStudents");
	}

	@RequestMapping(value = "/editUserExitTicket", method = RequestMethod.GET)
	public ModelAndView editUserExitTicket(HttpServletRequest request) {
		int exitTicketId = Integer.parseInt(request.getParameter("id"));
		UserExitTicket userExitTicket = userExitTicketService.getUserExitTicket(exitTicketId);
		ModelAndView model = new ModelAndView("userExitTicketForm");
		model.addObject("userExitTicket", userExitTicket);
		
		getUserAndETLists(model);

		return model;
	}
	
	public void getUserAndETLists(ModelAndView m) {
		// User ID
		Map<Integer, Integer> userIdsList = new LinkedHashMap<Integer, Integer>();
		List a = userService.listUserIds();
		for (int i = 0; i < a.size(); i++) {
			userIdsList.put((int) a.get(i), (int) a.get(i));
		}
		m.addObject("userIdsList", userIdsList);

		// ExitTicket ID
		Map<Integer, Integer> etIdsList = new LinkedHashMap<Integer, Integer>();
		List b = exitTicketService.listExitTicketsIds();
		for (int i = 0; i < b.size(); i++) {
			etIdsList.put((int) b.get(i), (int) b.get(i));
		}
		m.addObject("etIdsList", etIdsList);
	}

}