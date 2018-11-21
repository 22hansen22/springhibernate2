package com.jwt.controller;

import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.jwt.model.User;
import com.jwt.model.UserExitTicket;
import com.jwt.model.ExitTicket;
import com.jwt.service.UserService;
import com.jwt.service.UserExitTicketService;
import com.jwt.service.ExitTicketService;

@Controller
@RequestMapping("/user")
public class GroupController {
	
	@Autowired
	private UserService userService;
	
	private static Logger log = Logger.getLogger(GroupController.class);
	
	@RequestMapping(value={"/groupStudents"}, method = RequestMethod.GET)
	public ModelAndView listStudents(){    
		log.info("entro en teacherGroup");
	    LinkedList<String> list = getStudentsRealNames();
	    ModelAndView mv = new ModelAndView("groupStudents");
	    mv.addObject("lists", list);
	
	    return mv;
	}
	
	@RequestMapping(value={"/groupStudents"},params = "group", method = RequestMethod.GET)
	public ModelAndView listStudents2(@RequestParam("groupSize")String groupSize){    
		log.info("entro en group Students ");
	    LinkedList<String> list = getStudentsRealNames();
	    ModelAndView mv = new ModelAndView("groupStudents");
	    mv.addObject("lists", list);
	    
	    LinkedList<String> listc=(LinkedList)list.clone();
	    Collections.shuffle(listc);
	    LinkedList<LinkedList<String>> slist = new LinkedList<LinkedList<String>>();
	    //int groupSize=3;
	    int groupSizex=Integer.parseInt(groupSize);
	    int numGroups=listc.size()/groupSizex;
	    if (listc.size() % groupSizex >0) numGroups+=1;
	    for (int i=0; i<numGroups;i++) {
	    	slist.add(new LinkedList<String>());
	    }
	    int counter1=0;
	    for (int i=0; i<listc.size();i++) {
	    	slist.get(counter1).add(listc.get(i));
	    	if(((i+1) % groupSizex)==0)	counter1+=1;
	    }
	    mv.addObject("slists", slist);
	    String gmsg="Making groups of "+groupSizex +" people";
	    mv.addObject("gmsg", gmsg);

	
	    return mv;
	}
	
	
	private LinkedList<String> getStudentsRealNames(){
	    LinkedList<String> list = new LinkedList<String>();
	    
	    //list all users
	    log.info("Listing all users");

	    try {
		    List<User> listU=userService.getAllUsers();
		    log.info("Im out "+listU.size());
		    for (int i=0; i<listU.size(); i++) {
		    	//only add students to the list 
		    	if(listU.get(i).getType().equals("student")) {
		    		list.add(listU.get(i).getRealname());
		    	}
		    }
		    return list;
	    }catch(Exception e) {
		    log.error("Listing unsuccesful");
	    	return null;
	    	
	    }
	}
}