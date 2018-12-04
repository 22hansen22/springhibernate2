package com.jwt.controller;

import java.io.IOException;
import java.util.HashMap;
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
import com.jwt.model.UserClassDay;
import com.jwt.model.UserExitTicket;
import com.jwt.model.ClassDay;
import com.jwt.model.ExitTicket;
import com.jwt.service.UserService;
import com.jwt.service.UserExitTicketService;
import com.jwt.service.ClassDayService;
import com.jwt.service.ExitTicketService;
import com.jwt.service.UserClassDayService;

@Controller
@RequestMapping("/user")
public class UserController {

	private static final Logger log = Logger
			.getLogger(UserController.class);

	public UserController() {
		System.out.println("UserController()");
	}

	@Autowired
	private UserService userService;
	@Autowired
	private ExitTicketService xxService;
	@Autowired
	private UserExitTicketService userExitTicketService;
	@Autowired
	private ClassDayService classDayService;
	@Autowired
	private UserClassDayService userClassDayService;

	@RequestMapping(value = "/manageStudents")
	public ModelAndView listUser(ModelAndView model) throws IOException {
		List<User> listUser = userService.getAllUsers();
		model.addObject("listUser", listUser);
		
		List<ExitTicket> listExitTicket = xxService.getAllExitTickets();
		model.addObject("listExitTicket", listExitTicket);
		
		List<UserExitTicket> listUserExitTicket = userExitTicketService.getAllUserExitTickets();
		model.addObject("listUserExitTicket", listUserExitTicket);
		
		List<ClassDay> listClassDay = classDayService.getAllClassDays();
		model.addObject("listClassDay", listClassDay);
		
		List<UserClassDay> listUserClassDay = userClassDayService.getAllUserClassDays();
		model.addObject("listUserClassDay", listUserClassDay);
		
		model.setViewName("manageStudents");
		return model;
	}

	@RequestMapping(value = "/newUser", method = RequestMethod.GET)
	public ModelAndView newContact(ModelAndView model) {
		User user = new User();
		model.addObject("user", user);
		model.setViewName("userForm");
		
		Map<String,String> typesOfUser = new LinkedHashMap<String,String>();
		typesOfUser.put("student", "student");
		typesOfUser.put("teacher", "teacher");
		model.addObject("typeList", typesOfUser);
		
		return model;
	}

	@RequestMapping(value = "/saveUser", method = RequestMethod.POST)
	public ModelAndView saveUser(@ModelAttribute User user) {
		if (user.getId() == 0) { // if user id is 0 then creating the
			// user other updating the user
			userService.addUser(user);
		} else {
			userService.updateUser(user);
		}
		return new ModelAndView("redirect:/user/manageStudents");
	}

	@RequestMapping(value = "/deleteUser", method = RequestMethod.GET)
	public ModelAndView deleteUser(HttpServletRequest request) {
		int userId = Integer.parseInt(request.getParameter("id"));
		userService.deleteUser(userId);
		return new ModelAndView("redirect:/user/manageStudents");
	}

	@RequestMapping(value = "/editUser", method = RequestMethod.GET)
	public ModelAndView editContact(HttpServletRequest request) {
		log.info("entro en edit User");
		int userId = Integer.parseInt(request.getParameter("id"));
		User user = userService.getUser(userId);

		ModelAndView model = new ModelAndView("userForm");
		
		Map<String,String> typesOfUser = new LinkedHashMap<String,String>();
		typesOfUser.put("student", "student");
		typesOfUser.put("teacher", "teacher");
		model.addObject("typeList", typesOfUser);
		model.addObject("user", user);

		return model;
	}

}