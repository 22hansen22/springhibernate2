package com.jwt.service;

import java.util.List;
import com.jwt.model.UserClassDay;
import com.jwt.model.ClassDay;

public interface UserClassDayService {
	
	public void addUserClassDay(UserClassDay classDay);

	public List<UserClassDay> getAllUserClassDays();

	public void deleteUserClassDay(Integer classDayId);

	public UserClassDay getUserClassDay(int employeeClassDayid);

	public UserClassDay updateUserClassDay(UserClassDay classDay);
	
	public List<UserClassDay> findClassDaysByUser(int userId);
	
	public List<UserClassDay> findUsersByClassDay(int classDayId);
	
	public int countCDByUserID(int userId);
	
	public int countUsersByCDID(int classDayId);
	
	public List<Boolean> getWrittenYN(List<ClassDay> etList, int userId);
	
	public UserClassDay getUserClassDayByUserAndCD(int etId, int userId);
}
