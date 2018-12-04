package com.jwt.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jwt.model.User;
import com.jwt.model.ClassDay;
import com.jwt.model.SortByDate;
import com.jwt.model.SortByDateClassDay;

@Repository
public class ClassDayDAOImpl implements ClassDayDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public void addClassDay(ClassDay classDay) {
		sessionFactory.getCurrentSession().saveOrUpdate(classDay);

	}

	@SuppressWarnings("unchecked")
	public List<ClassDay> getAllClassDays() {

		return sessionFactory.getCurrentSession().createQuery("from ClassDay")
				.list();
	}

	@Override
	public void deleteClassDay(Integer classDayId) {
		ClassDay classDay = (ClassDay) sessionFactory.getCurrentSession().load(
				ClassDay.class, classDayId);
		if (null != classDay) {
			this.sessionFactory.getCurrentSession().delete(classDay);
		}

	}

	public ClassDay getClassDay(int classDayid) {
		return (ClassDay) sessionFactory.getCurrentSession().get(
				ClassDay.class, classDayid);
	}

	@Override
	public ClassDay updateClassDay(ClassDay classDay) {
		sessionFactory.getCurrentSession().update(classDay);
		return classDay;
	}
	
	public List<Integer> listClassDaysIds(){
		
	    try {
	    	List<Integer> listOfClassDayIDs=new ArrayList();
	    	List<ClassDay> listET = this.getAllClassDays();
	    	Collections.sort(listET, new SortByDateClassDay());
			Collections.reverse(listET);
			
		    for(int i=0; i<listET.size();i++) {
		    	listOfClassDayIDs.add((int) listET.get(i).getId());
		    }
		    return listOfClassDayIDs;
	    }catch(Exception e) {
			//log.error("An error occurred in listClassDaysIds -", e);	
	    }
		return null;
	}

}