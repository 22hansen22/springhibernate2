package com.jwt.controller;

import java.io.IOException;
import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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
import com.jwt.service.ClassDayService;
import com.jwt.service.UserClassDayService;

@Controller
@RequestMapping("/user")
public class ClassDayController {

	private static final Logger log = Logger
			.getLogger(ClassDayController.class);

	public ClassDayController() {
		System.out.println("ClassDayController()");
	}
	
	@Autowired
	private ClassDayService classDayService;
	@Autowired
	private UserService userService;
	@Autowired
	private UserClassDayService userClassDayService;
	
	@RequestMapping(value={"/attendanceTeacher"},params = "showCDInput", method = RequestMethod.GET)
	public ModelAndView showCDInput(){    
	    ModelAndView mv = new ModelAndView("attendanceTeacher");
	    mv.addObject("showCDInput", true);
	    mv.addObject("command", new ClassDay());
	    return mv;
	}
	
	@RequestMapping(value = "/attendanceTeacher/addClassDay", method = RequestMethod.GET)
	public String addUser(@ModelAttribute("SpringWeb") ClassDay classDay, ModelMap model) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy.MM.dd");
		LocalDate localDate = LocalDate.now();
		classDay.setDateClass(dtf.format(localDate));	
		classDay.setPassword(createRandomCode(4, "123456789ABCDEFGHIJ"));
		
		classDayService.addClassDay(classDay);
		
		return "redirect:/user/attendanceTeacher?showCDList=showCDList";
	}
	
	@RequestMapping(value={"/attendanceTeacher"},params = "showCDList", method = RequestMethod.GET)
	public ModelAndView showCDList(@RequestParam("showCDList") String listType,@RequestParam(value="id", required=false) Integer id){    
		log.info("entro en ClassDay -showCDList");
	    ModelAndView mv = new ModelAndView("attendanceTeacher");
	    mv.addObject("showCDList", listType);
	    
	    String headerx="";
	    if(listType.equals("showCDList")) {
	    	List<ClassDay> etList = classDayService.getAllClassDays();
	    	Collections.sort(etList, new SortByDateClassDay());
			Collections.reverse(etList);
			
	    	mv.addObject("etList", etList);
    		mv.addObject("countList2", getNumberOfUsersPerCD());
	    }
	    else if(listType.equals("showUserList")) {
	    	List<User> usersList = userService.getAllStudents();
    		mv.addObject("usersList", usersList);
    		mv.addObject("countList", getNumberOfCDPerUser());
	    }
	    else if(listType.equals("showUsersForCD")) {
	    	List<UserClassDay> usersForCD = getListUserByCD(id);
	    	mv.addObject("usersForCD", usersForCD);
	    }
	    else if(listType.equals("showCDForUser")) {
	    	List<UserClassDay> etForUsers = getListCDByUser(id);
	    	mv.addObject("etForUsers", etForUsers);
	    }
	    else if(listType.equals("deleteItem")) {
	    	if (id!=null) {
	    		log.info("entro en delete item-> "+id);
	    		classDayService.deleteClassDay(id);
	    		log.info("deleted entry with ID#="+id);
	    		return new ModelAndView("redirect:/user/attendanceTeacher?showCDList=showCDList");
	    	}
	    }
	    
	    mv.addObject("headerx", headerx);
	    log.info("param->"+listType);
	    return mv;
	}
	
	@RequestMapping(value = "/attendanceStudent")
	public ModelAndView goToSCT2(HttpSession session) {
		List<ClassDay> etList = classDayService.getAllClassDays();
		//order them by date
		Collections.sort(etList, new SortByDateClassDay());
		Collections.reverse(etList);
		int userId=Integer.parseInt(session.getAttribute("userId").toString());
		List<Boolean> writtenYN = userClassDayService.getWrittenYN(etList,userId);
		
		ModelAndView mv=new ModelAndView();
		mv.setViewName("attendanceStudent");
    	mv.addObject("etList", etList);
    	mv.addObject("writtenYN", writtenYN);
	    return mv;
	}
	
	@RequestMapping(value={"/attendanceStudent"},params = "showCDInputStudent", method = RequestMethod.GET)
	public ModelAndView showCDInput2(@RequestParam(value="classDayId", required=false) Integer classDayId,@RequestParam(value="password", required=false) String password, HttpSession session){    
		if (password==null)	password="";
		List<ClassDay> etList = new ArrayList<ClassDay>();
		ClassDay cd=classDayService.getClassDay(classDayId);
		
		if(password.equals(cd.getPassword())) {
			ModelAndView mv = new ModelAndView("attendanceStudent");
			etList.add(classDayService.getClassDay(classDayId));
			mv.addObject("showCDInputStudent", true);
			mv.addObject("classDayId", classDayId);
			mv.addObject("command", new UserClassDay());
			mv.addObject("etList", etList);	
			return mv;
		}
		else 	
			return new ModelAndView("redirect:/user/attendanceStudent");
		
	    
	}
	
	@RequestMapping(value = "/attendanceStudent/addAnswerToUserClassDay", method = RequestMethod.GET)
	public String addAnswerToCD(@ModelAttribute("SpringWeb") UserClassDay userClassDay, ModelMap model,HttpSession session,@RequestParam(value="classDayId", required=false) Integer classDayId) {
		userClassDay.setUser(userService.getUser(Integer.parseInt(session.getAttribute("userId").toString())));
		userClassDay.setClassDay(classDayService.getClassDay(classDayId));
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy.MM.dd");
		LocalDate localDate = LocalDate.now();
		userClassDay.setDateAnswer(dtf.format(localDate));	
		
		userClassDayService.addUserClassDay(userClassDay);
		
		return "redirect:/user/attendanceStudent";
	}
	
	@RequestMapping(value={"/attendanceStudent"},params = "showCDStudent", method = RequestMethod.GET)
	public ModelAndView showCDInput3(@RequestParam(value="classDayId", required=false) Integer classDayId, HttpSession session){    
		List<ClassDay> etList = new ArrayList<ClassDay>();
		etList.add(classDayService.getClassDay(classDayId));
		
	    ModelAndView mv = new ModelAndView("attendanceStudent");
	    mv.addObject("showCDStudent", true);
	    mv.addObject("classDayId", classDayId);
	    UserClassDay uCD=userClassDayService.getUserClassDayByUserAndCD(classDayId,Integer.parseInt(session.getAttribute("userId").toString()));
		
		mv.addObject("etList", etList);
	    mv.addObject("answerCD", uCD.getAnswer());
	    mv.addObject("command", uCD);
	    return mv;
	}

	
	
	//---------------------------------------------------------------------------------------------------------
	//functionality to add edit and delete Exit Tickets from the manage Users

	@RequestMapping(value = "/newClassDay", method = RequestMethod.GET)
	public ModelAndView newClassDay(ModelAndView model) {
		ClassDay classDay = new ClassDay();
		model.addObject("classDay", classDay);
		model.setViewName("classDayForm");
		return model;
	}

	@RequestMapping(value = "/saveClassDay", method = RequestMethod.POST)
	public ModelAndView saveClassDay(@ModelAttribute ClassDay classDay) {
		if (classDay.getId() == 0) { // if classDay id is 0 then creating the
			// classDay other updating the classDay
			classDayService.addClassDay(classDay);
		} else {
			classDayService.updateClassDay(classDay);
		}
		return new ModelAndView("redirect:/user/manageStudents");
	}

	@RequestMapping(value = "/deleteClassDay", method = RequestMethod.GET)
	public ModelAndView deleteClassDay(HttpServletRequest request) {
		int classDayId = Integer.parseInt(request.getParameter("id"));
		classDayService.deleteClassDay(classDayId);
		return new ModelAndView("redirect:/user/manageStudents");
	}

	@RequestMapping(value = "/editClassDay", method = RequestMethod.GET)
	public ModelAndView editContact(HttpServletRequest request) {
		int classDayId = Integer.parseInt(request.getParameter("id"));
		ClassDay classDay = classDayService.getClassDay(classDayId);
		ModelAndView model = new ModelAndView("classDayForm");
		model.addObject("classDay", classDay);

		return model;
	}
	
	// AUX methods -----------------------------------------

	private List<Integer> getNumberOfCDPerUser(){
		List<Integer> output=new ArrayList();
		List<Integer> l=userService.listUserIds();
		for(int i=0;i<l.size(); i++) {
			output.add(userClassDayService.countCDByUserID(l.get(i)));
		}
		return output;
	}
	
	private List<Integer> getNumberOfUsersPerCD(){
		List<Integer> output=new ArrayList();
		List<Integer> l=classDayService.listClassDaysIds();
		for(int i=0;i<l.size(); i++) {
			output.add(userClassDayService.countUsersByCDID(l.get(i)));
		}
		return output;
	}
	
	private List<UserClassDay> getListUserByCD(int id){
	    try {
		    List<UserClassDay> listCDbyUser=userClassDayService.findUsersByClassDay(id);
		    return listCDbyUser;
	    }catch(Exception e) {
		    log.error("Listing unsuccesful");
	    	return null;
	    }
	}
	
	private List<UserClassDay> getListCDByUser(int id){
	    try {
		    List<UserClassDay> listCDbyUser=userClassDayService.findClassDaysByUser(id);
		    return listCDbyUser;
	    }catch(Exception e) {
		    log.error("Listing unsuccesful");
	    	return null;
	    }
	}
	
	public static String createRandomCode(int codeLength, String id) {

	    return new SecureRandom().ints(codeLength, 0, id.length()).mapToObj(id::charAt).map(Object::toString).collect(Collectors.joining());

	}

}