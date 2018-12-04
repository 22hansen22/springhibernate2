package com.jwt.dao;

import java.util.List;
import com.jwt.model.ClassDay;

public interface ClassDayDAO {

	public void addClassDay(ClassDay classDay);

	public List<ClassDay> getAllClassDays();

	public void deleteClassDay(Integer classDayId);

	public ClassDay updateClassDay(ClassDay classDay);

	public ClassDay getClassDay(int classDayid);
	
	public List<Integer> listClassDaysIds();
}
