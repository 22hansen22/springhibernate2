package com.jwt.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jwt.model.User;
import com.jwt.model.UserExitTicket;
import com.jwt.model.ExitTicket;

@Repository
public class UserExitTicketDAOImpl implements UserExitTicketDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	private static final Logger log = Logger.getLogger(UserExitTicketDAOImpl.class);

	public void addUserExitTicket(UserExitTicket userExitTicket) {
		ExitTicket exitTicket = (ExitTicket) sessionFactory.getCurrentSession().get(ExitTicket.class, userExitTicket.getExitTicket().getId());
		userExitTicket.setExitTicket(exitTicket);
		User emp = (User) sessionFactory.getCurrentSession().get(User.class, userExitTicket.getUser().getId());
		userExitTicket.setUser(emp);;
		sessionFactory.getCurrentSession().saveOrUpdate(userExitTicket);

	}

	@SuppressWarnings("unchecked")
	public List<UserExitTicket> getAllUserExitTickets() {

		return sessionFactory.getCurrentSession().createQuery("from UserExitTicket")
				.list();
	}

	@Override
	public void deleteUserExitTicket(Integer exitTicketId) {
		UserExitTicket exitTicket = (UserExitTicket) sessionFactory.getCurrentSession().load(
				UserExitTicket.class, exitTicketId);
		if (null != exitTicket) {
			this.sessionFactory.getCurrentSession().delete(exitTicket);
		}

	}

	public UserExitTicket getUserExitTicket(int exitTicketid) {
		return (UserExitTicket) sessionFactory.getCurrentSession().get(
				UserExitTicket.class, exitTicketid);
	}

	@Override
	public UserExitTicket updateUserExitTicket(UserExitTicket exitTicket) {
		sessionFactory.getCurrentSession().update(exitTicket);
		return exitTicket;
	}
	
	public List<UserExitTicket> findExitTicketsByUser(int userId){
	    try {
	    	Query query = sessionFactory.getCurrentSession().createQuery(" FROM UserExitTicket u WHERE u.user.id = :userId");
			query.setString("userId", Integer.toString(userId));
		    List<UserExitTicket> list = (List<UserExitTicket>)query.list();
		    return list;
	    }catch(Exception e) {
			log.error("An error occurred while querying the database", e);	
	    }
		return null;
	}
	
	public List<UserExitTicket> findUsersByExitTicket(int exitTicketId){
	    try {
	    	Query query = sessionFactory.getCurrentSession().createQuery(" FROM UserExitTicket u WHERE u.exitTicket.id = :exitTicketId");
			query.setString("exitTicketId", Integer.toString(exitTicketId));
			
		    List<UserExitTicket> list = (List<UserExitTicket>)query.list();
		    return list;
	    }catch(Exception e) {
			log.error("An error occurred while querying the database", e);	
	    }
		return null;
		
	}
	
	public int countETByUserID(int userId) {
		List<UserExitTicket> l=findExitTicketsByUser(userId);
		return l.size();
	}
	
	public int countUsersByETID(int exitTicketId) {
		List<UserExitTicket> l=findUsersByExitTicket(exitTicketId);
		return l.size();
	}
	
	public List<Boolean> getWrittenYN(List<ExitTicket> etList, int userId){
		List<Boolean> output=new ArrayList<Boolean>();
		for(int i=0; i<etList.size();i++) {
			//check that for that ET you have a response yes or not
			Query query = sessionFactory.getCurrentSession().createQuery("FROM UserExitTicket u WHERE u.exitTicket.id = :exitTicketId AND u.user.id = :userId ");
			query.setString("exitTicketId", Integer.toString(etList.get(i).getId()));
			query.setString("userId", Integer.toString(userId));
			List<UserExitTicket> list = (List<UserExitTicket>)query.list();
			
			if(list.isEmpty())
				output.add(false);
			else
				output.add(true);
		}
		return output;
		
	}
	
	public UserExitTicket getUserExitTicketByUserAndET(int etId, int userId){
		
		Query query = sessionFactory.getCurrentSession().createQuery("FROM UserExitTicket u WHERE u.exitTicket.id = :exitTicketId AND u.user.id = :userId ");
		query.setString("exitTicketId", Integer.toString(etId));
		query.setString("userId", Integer.toString(userId));
		List<UserExitTicket> list = (List<UserExitTicket>)query.list();
		
		return list.get(0);
	}

}