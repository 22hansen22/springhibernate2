package com.jwt.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
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
	public ModelAndView goUpMinutes(HttpSession session, @RequestParam("timeCountDown")int timeCountDown) {
		//preconditons
		Assert.isTrue(timeCountDown>=0 && timeCountDown<60*60, "timeCountDown precondition range is incorrect");
		if (timeCountDown<0) timeCountDown=0;
		else if (timeCountDown>60*60) timeCountDown=60*60;
		
		ModelAndView mv = new ModelAndView();
		if (timeCountDown < (60*60-60))
			timeCountDown+=60;
		
		//post conditions
		Assert.isTrue(timeCountDown<=60*60, "timeCountDown greater than 60 min");
		
		mv.addObject("timeCountDown", timeCountDown);
		mv.setViewName("timer");
	    return mv;
	}
	
	@RequestMapping(value = "/goDownMinutes")
	public ModelAndView goDownMinutes(HttpSession session, @RequestParam("timeCountDown")int timeCountDown) {
		//preconditons
		Assert.isTrue(timeCountDown>=0 && timeCountDown<60*60, "timeCountDown precondition range is incorrect");
		if (timeCountDown<0) timeCountDown=0;
		else if (timeCountDown>60*60) timeCountDown=60*60;
		
		ModelAndView mv = new ModelAndView();
		if (timeCountDown >= 60)		timeCountDown-=60;
		
		//post conditions
		Assert.isTrue(timeCountDown>=0, "timeCountDown less than 0 min");
				
		mv.addObject("timeCountDown", timeCountDown);
		mv.setViewName("timer");
	    return mv;
	}
	
	@RequestMapping(value = "/goUpSeconds")
	public ModelAndView goUpSeconds(HttpSession session, @RequestParam("timeCountDown")int timeCountDown) {
		//preconditons
		Assert.isTrue(timeCountDown>=0 && timeCountDown<60*60, "timeCountDown precondition range is incorrect");
		if (timeCountDown<0) timeCountDown=0;
		else if (timeCountDown>60*60) timeCountDown=60*60;
		
		ModelAndView mv = new ModelAndView();
		if (timeCountDown < (60*60-30))
			timeCountDown+=30;
		
		//post conditions
		Assert.isTrue(timeCountDown<=60*60, "timeCountDown greater than 60 min");
				
		mv.addObject("timeCountDown", timeCountDown);
		mv.setViewName("timer");
	    return mv;
	}
	
	@RequestMapping(value = "/goDownSeconds")
	public ModelAndView goDownSeconds(HttpSession session, @RequestParam("timeCountDown")int timeCountDown) {
		//preconditons
		Assert.isTrue(timeCountDown>=0 && timeCountDown<60*60, "timeCountDown precondition range is incorrect");
		if (timeCountDown<0) timeCountDown=0;
		else if (timeCountDown>60*60) timeCountDown=60*60;
		
		ModelAndView mv = new ModelAndView();
		if (timeCountDown > 30)
			timeCountDown-=30;
		
		//post conditions
		Assert.isTrue(timeCountDown>=0, "timeCountDown less than 0 sec");
				
		mv.addObject("timeCountDown", timeCountDown);
		mv.setViewName("timer");
	    return mv;
	}
}