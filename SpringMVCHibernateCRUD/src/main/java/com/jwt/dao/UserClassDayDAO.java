package com.jwt.dao;

import java.util.List;
import com.jwt.model.User;
import com.jwt.model.UserClassDay;
import com.jwt.model.ClassDay;

public interface UserClassDayDAO {

	public void addUserClassDay(UserClassDay classDay);

	public List<UserClassDay> getAllUserClassDays();

	public void deleteUserClassDay(Integer classDayId);

	public UserClassDay updateUserClassDay(UserClassDay classDay);

	public UserClassDay getUserClassDay(int classDayid);
	
	public List<UserClassDay> findClassDaysByUser(int userId);
	
	public List<UserClassDay> findUsersByClassDay(int classDayId);
	
	public int countCDByUserID(int userId);
	
	public int countUsersByCDID(int classDayId);
	
	public List<Boolean> getWrittenYN(List<ClassDay> etList, int userId);
	
	public UserClassDay getUserClassDayByUserAndCD(int etId, int userId);
}
