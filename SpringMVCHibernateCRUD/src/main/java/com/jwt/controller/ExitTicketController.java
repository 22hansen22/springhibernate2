package com.jwt.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.jwt.model.*;
import com.jwt.service.UserService;
import com.jwt.service.ExitTicketService;
import com.jwt.service.UserExitTicketService;

@Controller
@RequestMapping("/user")
public class ExitTicketController {

	private static final Logger log = Logger
			.getLogger(ExitTicketController.class);

	public ExitTicketController() {
		System.out.println("ExitTicketController()");
	}

	@Autowired
	private ExitTicketService exitTicketService;
	@Autowired
	private UserService userService;
	@Autowired
	private UserExitTicketService userExitTicketService;

	//show form for creating an exit ticket
	@RequestMapping(value={"/exitTicketTeacher"},params = "showETInput", method = RequestMethod.GET)
	public ModelAndView showETInput(){    
	    ModelAndView mv = new ModelAndView("exitTicketTeacher");
	    mv.addObject("showETInput", true);
	    mv.addObject("command", new ExitTicket());
	    return mv;
	}
	
	// add an instance of Exit Ticket from the form
	@RequestMapping(value = "/exitTicketTeacher/addExitTicket", method = RequestMethod.GET)
	public String addExitTicket(@ModelAttribute("SpringWeb") ExitTicket exitTicketEntry) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy.MM.dd");
		LocalDate localDate = LocalDate.now();
		exitTicketEntry.setDateET(dtf.format(localDate));	
		exitTicketService.addExitTicket(exitTicketEntry);
		
		return "redirect:/user/exitTicketTeacher?showETList=showETList";
	}
	
	//show list of Exit Tickets and Users
	@RequestMapping(value={"/exitTicketTeacher"},params = "showETList", method = RequestMethod.GET)
	public ModelAndView showETList(@RequestParam("showETList") String listType,@RequestParam(value="id", required=false) Integer id){    
		log.info("entro en exitTicketT-showETList");
	    ModelAndView mv = new ModelAndView("exitTicketTeacher");
	    mv.addObject("showETList", listType);
	    
	    String headerx="";
	    if(listType.equals("showETList")) {
	    	List<ExitTicket> etList = exitTicketService.getAllExitTickets();
	    	Collections.sort(etList, new SortByDate());
			Collections.reverse(etList);
			
	    	mv.addObject("etList", etList);
    		mv.addObject("countList2", getNumberOfUsersPerET());
	    }
	    else if(listType.equals("showUserList")) {
	    	List<User> usersList = userService.getAllStudents();
    		mv.addObject("usersList", usersList);
    		mv.addObject("countList", getNumberOfETPerUser());
	    }
	    else if(listType.equals("showUsersForET")) {
	    	List<UserExitTicket> usersForET = getListUserByET(id);
	    	mv.addObject("usersForET", usersForET);
	    }
	    else if(listType.equals("showETForUser")) {
	    	List<UserExitTicket> etForUsers = getListETByUser(id);
	    	mv.addObject("etForUsers", etForUsers);
	    }
	    else if(listType.equals("deleteItem")) {
	    	if (id!=null) {
	    		log.info("entro en delete item-> "+id);
	    		exitTicketService.deleteExitTicket(id);
	    		log.info("deleted entry with ID#="+id);
	    		return new ModelAndView("redirect:/user/exitTicketTeacher?showETList=showETList");
	    	}
	    }
	    mv.addObject("headerx", headerx);
	    log.info("param->"+listType);
	    return mv;
	}
	
	// Student section ----------------------------
	
	@RequestMapping(value = "/exitTicketStudent")
	public ModelAndView goExitTicketStudent(HttpSession session) {
		List<ExitTicket> etList = exitTicketService.getAllExitTickets();
		//order them by date
		Collections.sort(etList, new SortByDate());
		Collections.reverse(etList);
		int userId=Integer.parseInt(session.getAttribute("userId").toString());
		List<Boolean> writtenYN = userExitTicketService.getWrittenYN(etList,userId);
		
		ModelAndView mv=new ModelAndView();
		mv.setViewName("exitTicketStudent");
    	mv.addObject("etList", etList);
    	mv.addObject("writtenYN", writtenYN);
	    return mv;
	}
	
	@RequestMapping(value={"/exitTicketStudent"},params = "showETInputStudent", method = RequestMethod.GET)
	public ModelAndView showETInputStudent(@RequestParam(value="exitTicketId", required=false) Integer exitTicketId, HttpSession session){    
		List<ExitTicket> etList = new ArrayList<ExitTicket>();
		etList.add(exitTicketService.getExitTicket(exitTicketId));
		
	    ModelAndView mv = new ModelAndView("exitTicketStudent");
	    mv.addObject("showETInputStudent", true);
	    mv.addObject("exitTicketId", exitTicketId);
	    mv.addObject("command", new UserExitTicket());
	    mv.addObject("etList", etList);
	    return mv;
	}
	
	@RequestMapping(value = "/exitTicketStudent/addAnswerToUserExitTicket", method = RequestMethod.GET)
	public String addAnswerToET(@ModelAttribute("SpringWeb") UserExitTicket userExitTicket, HttpSession session,@RequestParam(value="exitTicketId", required=false) Integer exitTicketId) {
		userExitTicket.setUser(userService.getUser(Integer.parseInt(session.getAttribute("userId").toString())));
		userExitTicket.setExitTicket(exitTicketService.getExitTicket(exitTicketId));
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy.MM.dd");
		LocalDate localDate = LocalDate.now();
		userExitTicket.setDateAnswer(dtf.format(localDate));	
		
		userExitTicketService.addUserExitTicket(userExitTicket);
		
		return "redirect:/user/exitTicketStudent";
	}
	
	@RequestMapping(value={"/exitTicketStudent"},params = "showETStudent", method = RequestMethod.GET)
	public ModelAndView showETStudent(@RequestParam(value="exitTicketId", required=false) Integer exitTicketId, HttpSession session){    
		List<ExitTicket> etList = new ArrayList<ExitTicket>();
		etList.add(exitTicketService.getExitTicket(exitTicketId));
		
	    ModelAndView mv = new ModelAndView("exitTicketStudent");
	    mv.addObject("showETStudent", true);
	    mv.addObject("exitTicketId", exitTicketId);
	    UserExitTicket uET=userExitTicketService.getUserExitTicketByUserAndET(exitTicketId,Integer.parseInt(session.getAttribute("userId").toString()));
		
		mv.addObject("etList", etList);
	    mv.addObject("answerET", uET.getAnswer());
	    mv.addObject("command", uET);
	    return mv;
	}
	
	
	//---------------------------------------------------------------------------------------------------------
	//functionality to add edit and delete Exit Tickets from the manage Users

	@RequestMapping(value = "/newExitTicket", method = RequestMethod.GET)
	public ModelAndView newExitTicket(ModelAndView model) {
		ExitTicket exitTicket = new ExitTicket();
		model.addObject("exitTicket", exitTicket);
		model.setViewName("exitTicketForm");
		return model;
	}

	@RequestMapping(value = "/saveExitTicket", method = RequestMethod.POST)
	public ModelAndView saveExitTicket(@ModelAttribute ExitTicket exitTicket) {
		//if date incorrect/empty -> selecting today
		if (exitTicket.getDateET() == "") {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy.MM.dd");
			LocalDate localDate = LocalDate.now();
			exitTicket.setDateET(dtf.format(localDate));
		}
		
		if (exitTicket.getId() == 0) { // if exitTicket id is 0 then creating the
			// exitTicket other updating the exitTicket
			exitTicketService.addExitTicket(exitTicket);
		} else {
			exitTicketService.updateExitTicket(exitTicket);
		}
		return new ModelAndView("redirect:/user/manageStudents");
	}

	@RequestMapping(value = "/deleteExitTicket", method = RequestMethod.GET)
	public ModelAndView deleteExitTicket(HttpServletRequest request) {
		int exitTicketId = Integer.parseInt(request.getParameter("id"));
		exitTicketService.deleteExitTicket(exitTicketId);
		return new ModelAndView("redirect:/user/manageStudents");
	}

	@RequestMapping(value = "/editExitTicket", method = RequestMethod.GET)
	public ModelAndView editExitTicket(HttpServletRequest request) {
		int exitTicketId = Integer.parseInt(request.getParameter("id"));
		ExitTicket exitTicket = exitTicketService.getExitTicket(exitTicketId);
		ModelAndView model = new ModelAndView("exitTicketForm");
		model.addObject("exitTicket", exitTicket);

		return model;
	}
	
	// AUXiliary  methods -----------------------------------------
	
	private List<Integer> getNumberOfETPerUser(){
		List<Integer> output=new ArrayList();
		List<Integer> l=userService.listUserIds();
		for(int i=0;i<l.size(); i++) {
			output.add(userExitTicketService.countETByUserID(l.get(i)));
		}
		return output;
	}
	
	private List<Integer> getNumberOfUsersPerET(){
		List<Integer> output=new ArrayList();
		List<Integer> l=exitTicketService.listExitTicketsIds();
		for(int i=0;i<l.size(); i++) {
			output.add(userExitTicketService.countUsersByETID(l.get(i)));
		}
		return output;
	}
	
	private List<UserExitTicket> getListUserByET(int id){
	    try {
		    List<UserExitTicket> listETbyUser=userExitTicketService.findUsersByExitTicket(id);
		    return listETbyUser;
	    }catch(Exception e) {
		    log.error("Listing unsuccesful");
	    	return null;
	    }
	}
	
	private List<UserExitTicket> getListETByUser(int id){
	    try {
		    List<UserExitTicket> listETbyUser=userExitTicketService.findExitTicketsByUser(id);
		    return listETbyUser;
	    }catch(Exception e) {
		    log.error("Listing unsuccesful");
	    	return null;
	    }
	}
	

}