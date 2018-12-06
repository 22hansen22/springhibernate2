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
import com.jwt.model.UserClassDay;
import com.jwt.model.ClassDay;
import com.jwt.service.UserService;
import com.jwt.service.UserClassDayService;
import com.jwt.service.ClassDayService;
import com.jwt.service.ExitTicketService;

@Controller
@RequestMapping("/user")
public class UserClassDayController {

	private static final Logger logger = Logger
			.getLogger(UserClassDayController.class);

	public UserClassDayController() {
		System.out.println("UserClassDayController()");
	}

	@Autowired
	private UserClassDayService userClassDayService;
	@Autowired
	private UserService userService;
	@Autowired
	private ClassDayService classDayService;

	//redirect to the form to be able to add a new User Class Day
	@RequestMapping(value = "/newUserClassDay", method = RequestMethod.GET)
	public ModelAndView newUserClassDay(ModelAndView model) {
		UserClassDay userClassDay = new UserClassDay();
		model.addObject("userClassDay", userClassDay);
		model.setViewName("userClassDayForm");
		getUserAndETLists(model);
		return model;
	}

	//save the User Class Day entity to the DB
	@RequestMapping(value = "/saveUserClassDay", method = RequestMethod.POST)
	public ModelAndView saveUserClassDay(@ModelAttribute UserClassDay userClassDay) {
		// if date incorrect/empty -> selecting today
		if (userClassDay.getDateAnswer() == "") {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy.MM.dd");
			LocalDate localDate = LocalDate.now();
			userClassDay.setDateAnswer(dtf.format(localDate));
		}
		
		// if date incorrect/empty -> selecting today
		if (userClassDay.getClassDay().getDateClass() == "") {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy.MM.dd");
			LocalDate localDate = LocalDate.now();
			userClassDay.getClassDay().setDateClass(dtf.format(localDate));
		}
		
		if (userClassDay.getId() == 0) { 
			userClassDayService.addUserClassDay(userClassDay);
		} else {
			userClassDayService.updateUserClassDay(userClassDay);
		}
		return new ModelAndView("redirect:/user/manageStudents");
	}

	// Delete from the DB that specific User Class Day
	@RequestMapping(value = "/deleteUserClassDay", method = RequestMethod.GET)
	public ModelAndView deleteUserClassDay(HttpServletRequest request) {
		int classDayId = Integer.parseInt(request.getParameter("id"));
		userClassDayService.deleteUserClassDay(classDayId);
		return new ModelAndView("redirect:/user/manageStudents");
	}

	//edit the User Class Day knowing the ID
	@RequestMapping(value = "/editUserClassDay", method = RequestMethod.GET)
	public ModelAndView editUserClassDay(HttpServletRequest request) {
		int classDayId = Integer.parseInt(request.getParameter("id"));
		UserClassDay userClassDay = userClassDayService.getUserClassDay(classDayId);
		ModelAndView model = new ModelAndView("userClassDayForm");
		model.addObject("userClassDay", userClassDay);
		getUserAndETLists(model);
		return model;
	}
	
	//obtain the lists for userIdsList and classDayIdsList
	public void getUserAndETLists(ModelAndView m) {
		// User ID
		Map<Integer, Integer> userIdsList = new LinkedHashMap<Integer, Integer>();
		List a = userService.listUserIds();
		for (int i = 0; i < a.size(); i++) {
			userIdsList.put((int) a.get(i), (int) a.get(i));
		}
		m.addObject("userIdsList", userIdsList);

		// ExitTicket ID
		Map<Integer, Integer> classDayIdsList = new LinkedHashMap<Integer, Integer>();
		List b = classDayService.listClassDaysIds();
		for (int i = 0; i < b.size(); i++) {
			classDayIdsList.put((int) b.get(i), (int) b.get(i));
		}
		m.addObject("classDayIdsList", classDayIdsList);
	}

}