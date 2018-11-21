package com.jwt.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.jwt.model.User;

@Repository
public class UserDAOImpl implements UserDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public void addUser(User user) {
		sessionFactory.getCurrentSession().saveOrUpdate(user);

	}

	@SuppressWarnings("unchecked")
	public List<User> getAllUsers() {

		return sessionFactory.getCurrentSession().createQuery("from User")
				.list();
	}

	@Override
	public void deleteUser(Integer userId) {
		User user = (User) sessionFactory.getCurrentSession().load(
				User.class, userId);
		if (null != user) {
			this.sessionFactory.getCurrentSession().delete(user);
		}

	}

	public User getUser(int empId) {
		return (User) sessionFactory.getCurrentSession().get(
				User.class, empId);
	}

	@Override
	public User updateUser(User user) {
		sessionFactory.getCurrentSession().update(user);
		return user;
	}
	
	@SuppressWarnings( { "unchecked", "deprecation" } )
	public boolean findUser(String uname,String upwd) {
		boolean isValidUser = false;
		//String sqlQuery = "from User u where u.name = "+uname+ " and u.password = "+upwd;
		try {
			Query query = sessionFactory.getCurrentSession().createQuery(" FROM User u WHERE u.name = :uname AND u.password = :upwd ");
			query.setString("uname", uname);
			query.setString("upwd", upwd);
			
			List<User> userObj = (List<User>) query.list();
			if(userObj != null && userObj.size() > 0) {
				isValidUser = true;
			}
		} catch(Exception e) {
			isValidUser = false;
		}
		return isValidUser;
	}
	
	@SuppressWarnings( { "unchecked", "deprecation" } )
	public User getUserByNameAndPassword(String uname,String upwd) {
			if(findUser(uname, upwd)==true) {
				try {
					Query query = sessionFactory.getCurrentSession().createQuery(" FROM User u WHERE u.name = :uname AND u.password = :upwd ");
					query.setString("uname", uname);
					query.setString("upwd", upwd);
					
					List<User> userObj = (List<User>) query.list();
					return userObj.get(0);
				} catch(Exception e) {
					//logger.error("An error occurred while fetching the user details from the database2", e);	
				}
				
			}
			return null;
	}
	
	public List<Integer> listUserIds(){
	    try {
	    	List<Integer> listOfUserIDs=new ArrayList();
	    	Query query = sessionFactory.getCurrentSession().createQuery(" FROM User u WHERE u.type = :typeU ");
			query.setString("typeU", "student");
			
		    List<User> listU = (List<User>)query.list();
		    for(int i=0; i<listU.size();i++) {
		    	listOfUserIDs.add((int) listU.get(i).getId());
		    }
		    return listOfUserIDs;
	    }catch(Exception e) {
			//log.error("An error occurred while querying the database", e);	
	    }
		return null;
	}
	
	public List<User> getAllStudents(){
	    try {
	    	Query query = sessionFactory.getCurrentSession().createQuery(" FROM User u WHERE u.type = :typeU ");
			query.setString("typeU", "student");
			
		    List<User> listOfStudents = (List<User>)query.list();
		    
		    return listOfStudents;
	    }catch(Exception e) {
			//log.error("An error occurred while querying the database", e);	
	    }
		return null;
	}

}