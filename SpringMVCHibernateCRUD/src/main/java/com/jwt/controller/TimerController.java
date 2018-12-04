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
public class TimerController{
	
	private static final Logger log = Logger
			.getLogger(TimerController.class);
	
	@RequestMapping(value = "/goUpMinutes")
	public ModelAndView goUpMin(HttpSession session, @RequestParam("timeCountDown")int timeCountDown) {
		ModelAndView mv = new ModelAndView();
		if (timeCountDown < (60*60-60))
			timeCountDown+=60;
		mv.addObject("timeCountDown", timeCountDown);
		mv.setViewName("timer");
	    return mv;
	}
	
	@RequestMapping(value = "/goDownMinutes")
	public ModelAndView goDownMin(HttpSession session, @RequestParam("timeCountDown")int timeCountDown) {
		ModelAndView mv = new ModelAndView();
		if (timeCountDown > 60)
			timeCountDown-=60;
		mv.addObject("timeCountDown", timeCountDown);
		mv.setViewName("timer");
	    return mv;
	}
	
	@RequestMapping(value = "/goUpSeconds")
	public ModelAndView goUpSec(HttpSession session, @RequestParam("timeCountDown")int timeCountDown) {
		ModelAndView mv = new ModelAndView();
		if (timeCountDown < (60*60-30))
			timeCountDown+=30;
		mv.addObject("timeCountDown", timeCountDown);
		mv.setViewName("timer");
	    return mv;
	}
	
	@RequestMapping(value = "/goDownSeconds")
	public ModelAndView goDownSec(HttpSession session, @RequestParam("timeCountDown")int timeCountDown) {
		ModelAndView mv = new ModelAndView();
		if (timeCountDown > 30)
			timeCountDown-=30;
		mv.addObject("timeCountDown", timeCountDown);
		mv.setViewName("timer");
	    return mv;
	}
}