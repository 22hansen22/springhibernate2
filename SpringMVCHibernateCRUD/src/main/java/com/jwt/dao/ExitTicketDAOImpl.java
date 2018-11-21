package com.jwt.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jwt.model.User;
import com.jwt.model.ExitTicket;
import com.jwt.model.SortByDate;

@Repository
public class ExitTicketDAOImpl implements ExitTicketDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public void addExitTicket(ExitTicket employee) {
		sessionFactory.getCurrentSession().saveOrUpdate(employee);

	}

	@SuppressWarnings("unchecked")
	public List<ExitTicket> getAllExitTickets() {

		return sessionFactory.getCurrentSession().createQuery("from ExitTicket")
				.list();
	}

	@Override
	public void deleteExitTicket(Integer exitTicketId) {
		ExitTicket exitTicket = (ExitTicket) sessionFactory.getCurrentSession().load(
				ExitTicket.class, exitTicketId);
		if (null != exitTicket) {
			this.sessionFactory.getCurrentSession().delete(exitTicket);
		}

	}

	public ExitTicket getExitTicket(int exitTicketid) {
		return (ExitTicket) sessionFactory.getCurrentSession().get(
				ExitTicket.class, exitTicketid);
	}

	@Override
	public ExitTicket updateExitTicket(ExitTicket exitTicket) {
		sessionFactory.getCurrentSession().update(exitTicket);
		return exitTicket;
	}
	
	public List<Integer> listExitTicketsIds(){
		
	    try {
	    	List<Integer> listOfExitTicketIDs=new ArrayList();
	    	List<ExitTicket> listET = this.getAllExitTickets();
	    	Collections.sort(listET, new SortByDate());
			Collections.reverse(listET);
			
		    for(int i=0; i<listET.size();i++) {
		    	listOfExitTicketIDs.add((int) listET.get(i).getId());
		    }
		    return listOfExitTicketIDs;
	    }catch(Exception e) {
			//log.error("An error occurred in listExitTicketsIds -", e);	
	    }
		return null;
	}

}