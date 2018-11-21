package com.jwt.controller;

import java.io.IOException;
import java.util.List;

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

	@RequestMapping(value = "/newUserExitTicket", method = RequestMethod.GET)
	public ModelAndView newContact(ModelAndView model) {
		UserExitTicket userExitTicket = new UserExitTicket();
		model.addObject("userExitTicket", userExitTicket);
		model.setViewName("userExitTicketForm");
		return model;
	}

	@RequestMapping(value = "/saveUserExitTicket", method = RequestMethod.POST)
	public ModelAndView saveUserExitTicket(@ModelAttribute UserExitTicket userExitTicket) {
		if (userExitTicket.getId() == 0) { // if exitTicket id is 0 then creating the
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
	public ModelAndView editContact(HttpServletRequest request) {
		int exitTicketId = Integer.parseInt(request.getParameter("id"));
		UserExitTicket userExitTicket = userExitTicketService.getUserExitTicket(exitTicketId);
		ModelAndView model = new ModelAndView("userExitTicketForm");
		model.addObject("userExitTicket", userExitTicket);

		return model;
	}

}