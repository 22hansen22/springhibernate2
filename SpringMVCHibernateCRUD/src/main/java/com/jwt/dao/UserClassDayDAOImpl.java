package com.jwt.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jwt.model.User;
import com.jwt.model.UserClassDay;
import com.jwt.model.ClassDay;

@Repository
public class UserClassDayDAOImpl implements UserClassDayDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public void addUserClassDay(UserClassDay userClassDay) {
		ClassDay classDay = (ClassDay) sessionFactory.getCurrentSession().get(ClassDay.class, userClassDay.getClassDay().getId());
		userClassDay.setClassDay(classDay);
		User emp = (User) sessionFactory.getCurrentSession().get(User.class, userClassDay.getUser().getId());
		userClassDay.setUser(emp);
		sessionFactory.getCurrentSession().saveOrUpdate(userClassDay);

	}

	@SuppressWarnings("unchecked")
	public List<UserClassDay> getAllUserClassDays() {

		return sessionFactory.getCurrentSession().createQuery("from UserClassDay")
				.list();
	}

	@Override
	public void deleteUserClassDay(Integer classDayId) {
		UserClassDay classDay = (UserClassDay) sessionFactory.getCurrentSession().load(
				UserClassDay.class, classDayId);
		if (null != classDay) {
			this.sessionFactory.getCurrentSession().delete(classDay);
		}

	}

	public UserClassDay getUserClassDay(int classDayid) {
		return (UserClassDay) sessionFactory.getCurrentSession().get(
				UserClassDay.class, classDayid);
	}

	@Override
	public UserClassDay updateUserClassDay(UserClassDay classDay) {
		sessionFactory.getCurrentSession().update(classDay);
		return classDay;
	}
	
	public List<UserClassDay> findClassDaysByUser(int userId){
	    try {
	    	Query query = sessionFactory.getCurrentSession().createQuery(" FROM UserClassDay u WHERE u.user.id = :userId");
			query.setString("userId", Integer.toString(userId));
		    List<UserClassDay> list = (List<UserClassDay>)query.list();
		    return list;
	    }catch(Exception e) {
			//log.error("An error occurred while querying the database", e);	
	    }
		return null;
	}
	
	public List<UserClassDay> findUsersByClassDay(int classDayId){
	    try {
	    	Query query = sessionFactory.getCurrentSession().createQuery(" FROM UserClassDay u WHERE u.classDay.id = :classDayId");
			query.setString("classDayId", Integer.toString(classDayId));
			
		    List<UserClassDay> list = (List<UserClassDay>)query.list();
		    return list;
	    }catch(Exception e) {
			//log.error("An error occurred while querying the database", e);	
	    }
		return null;
		
	}
	
	public int countCDByUserID(int userId) {
		List<UserClassDay> l=findClassDaysByUser(userId);
		return l.size();
	}
	
	public int countUsersByCDID(int classDayId) {
		List<UserClassDay> l=findUsersByClassDay(classDayId);
		return l.size();
	}
	
	public List<Boolean> getWrittenYN(List<ClassDay> etList, int userId){
		List<Boolean> output=new ArrayList<Boolean>();
		for(int i=0; i<etList.size();i++) {
			//check that for that CD you have a response yes or not
			Query query = sessionFactory.getCurrentSession().createQuery("FROM UserClassDay u WHERE u.classDay.id = :classDayId AND u.user.id = :userId ");
			query.setString("classDayId", Integer.toString(etList.get(i).getId()));
			query.setString("userId", Integer.toString(userId));
			List<UserClassDay> list = (List<UserClassDay>)query.list();
			
			if(list.isEmpty())
				output.add(false);
			else
				output.add(true);
		}
		return output;
		
	}
	
	public UserClassDay getUserClassDayByUserAndCD(int etId, int userId){
		
		Query query = sessionFactory.getCurrentSession().createQuery("FROM UserClassDay u WHERE u.classDay.id = :classDayId AND u.user.id = :userId ");
		query.setString("classDayId", Integer.toString(etId));
		query.setString("userId", Integer.toString(userId));
		List<UserClassDay> list = (List<UserClassDay>)query.list();
		
		return list.get(0);
	}

}