package com.jwt.controller;

import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
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
	public ModelAndView groupStudents(){    
	    LinkedList<String> list = getStudentsRealNames();
	    ModelAndView mv = new ModelAndView("groupStudents");
	    mv.addObject("lists", list);
	
	    return mv;
	}
	
	//perform the grouping of the student list in mini groups
	@RequestMapping(value={"/groupStudents"},params = "group", method = RequestMethod.GET)
	public ModelAndView groupStudents2(@RequestParam("groupSize")String groupSize){    
		log.info("Inside group Students ");
	    LinkedList<String> listRealNames = getStudentsRealNames();
	    ModelAndView mv = new ModelAndView("groupStudents");
	    mv.addObject("listRealNames", listRealNames);
	    
	    LinkedList<String> listRealNamesTemp=(LinkedList)listRealNames.clone();
	    Collections.shuffle(listRealNamesTemp);
	    LinkedList<LinkedList<String>> listOfGroups = new LinkedList<LinkedList<String>>();
	    
	    int groupSizeParsed;
	    try {
	    	groupSizeParsed=Integer.parseInt(groupSize);
	    }catch(Exception e) {
	    	groupSizeParsed=2;
	    }
	    //preconditions
	    if (groupSizeParsed<2) groupSizeParsed=2;
	    else if (groupSizeParsed>5) groupSizeParsed=5;
	    
	    assert groupSizeParsed>=2 && groupSizeParsed<=5 : "range is incorrect";
	    
	    
	    //diving the total list of students in subgroups
	    int numGroups=listRealNamesTemp.size()/groupSizeParsed;
	    if (listRealNamesTemp.size() % groupSizeParsed >0) numGroups+=1;
	    for (int i=0; i<numGroups;i++) {
	    	listOfGroups.add(new LinkedList<String>());
	    }
	    int counter1=0;
	    for (int i=0; i<listRealNamesTemp.size();i++) {
	    	listOfGroups.get(counter1).add(listRealNamesTemp.get(i));
	    	if(((i+1) % groupSizeParsed)==0)	counter1+=1;
	    }
	    
	    
	    mv.addObject("listOfGroups", listOfGroups);
	    String gmsg="Making groups of "+groupSizeParsed +" people";
	    mv.addObject("gmsg", gmsg);

	    return mv;
	}
	
	
	private LinkedList<String> getStudentsRealNames(){
	    LinkedList<String> list = new LinkedList<String>();
	    
	    //list all users

	    try {
		    List<User> listUsers=userService.getAllUsers();
		    log.info("Im out "+listUsers.size());
		    for (int i=0; i<listUsers.size(); i++) {
		    	//only add students to the list - discard teachers
		    	if(listUsers.get(i).getType().equals("student")) {
		    		list.add(listUsers.get(i).getRealname());
		    	}
		    }
		    return list;
	    }catch(Exception e) {
		    log.error("Listing unsuccesful");
	    	return null;
	    	
	    }
	}
}