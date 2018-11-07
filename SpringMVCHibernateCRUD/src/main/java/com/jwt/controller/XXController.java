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
import com.jwt.model.Employee;
import com.jwt.model.XX;
import com.jwt.service.EmployeeService;
import com.jwt.service.XXService;

@Controller
public class XXController {

	private static final Logger logger = Logger
			.getLogger(XXController.class);

	public XXController() {
		System.out.println("XXController()");
	}

	@Autowired
	private XXService xxService;

	

	@RequestMapping(value = "/newXX", method = RequestMethod.GET)
	public ModelAndView newContact(ModelAndView model) {
		XX xx = new XX();
		model.addObject("xx", xx);
		model.setViewName("XXForm");
		return model;
	}

	@RequestMapping(value = "/saveXX", method = RequestMethod.POST)
	public ModelAndView saveXX(@ModelAttribute XX xx) {
		if (xx.getId() == 0) { // if xx id is 0 then creating the
			// xx other updating the xx
			xxService.addXX(xx);
		} else {
			xxService.updateXX(xx);
		}
		return new ModelAndView("redirect:/");
	}

	@RequestMapping(value = "/deleteXX", method = RequestMethod.GET)
	public ModelAndView deleteXX(HttpServletRequest request) {
		int xxId = Integer.parseInt(request.getParameter("id"));
		xxService.deleteXX(xxId);
		return new ModelAndView("redirect:/");
	}

	@RequestMapping(value = "/editXX", method = RequestMethod.GET)
	public ModelAndView editContact(HttpServletRequest request) {
		int xxId = Integer.parseInt(request.getParameter("id"));
		XX xx = xxService.getXX(xxId);
		ModelAndView model = new ModelAndView("XXForm");
		model.addObject("xx", xx);

		return model;
	}

}