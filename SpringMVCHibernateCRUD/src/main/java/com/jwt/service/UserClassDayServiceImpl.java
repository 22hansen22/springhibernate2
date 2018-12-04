package com.jwt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jwt.dao.UserDAO;
import com.jwt.dao.UserClassDayDAO;
import com.jwt.dao.ClassDayDAO;
import com.jwt.model.User;
import com.jwt.model.UserClassDay;
import com.jwt.model.ClassDay;

@Service
@Transactional
public class UserClassDayServiceImpl implements UserClassDayService {

	@Autowired
	private UserClassDayDAO userClassDayDAO;

	@Override
	@Transactional
	public void addUserClassDay(UserClassDay classDay) {
		userClassDayDAO.addUserClassDay(classDay);
	}

	@Override
	@Transactional
	public List<UserClassDay> getAllUserClassDays() {
		return userClassDayDAO.getAllUserClassDays();
	}

	@Override
	@Transactional
	public void deleteUserClassDay(Integer classDayId) {
		userClassDayDAO.deleteUserClassDay(classDayId);
	}

	public UserClassDay getUserClassDay(int employeeClassDayid) {
		return userClassDayDAO.getUserClassDay(employeeClassDayid);
	}

	public UserClassDay updateUserClassDay(UserClassDay classDay) {
		// TODO Auto-generated method stub
		return userClassDayDAO.updateUserClassDay(classDay);
	}

	public void setUserClassDayDAO(UserClassDayDAO userClassDayDAO) {
		this.userClassDayDAO = userClassDayDAO;
	}

	public List<UserClassDay> findClassDaysByUser(int userId){
		return userClassDayDAO.findClassDaysByUser(userId);
	}
	
	public List<UserClassDay> findUsersByClassDay(int classDayId){
		return userClassDayDAO.findUsersByClassDay(classDayId);
	}
	
	public int countCDByUserID(int userId) {
		return userClassDayDAO.countCDByUserID(userId);
	}
	
	public int countUsersByCDID(int classDayId) {
		return userClassDayDAO.countUsersByCDID(classDayId);
	}
	
	public List<Boolean> getWrittenYN(List<ClassDay> etList, int userId){
		return userClassDayDAO.getWrittenYN(etList,userId);
	}
	
	public UserClassDay getUserClassDayByUserAndCD(int etId, int userId) {
		return userClassDayDAO.getUserClassDayByUserAndCD(etId,userId);
	}
}
