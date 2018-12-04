package com.jwt.service;

import java.util.List;
import com.jwt.model.ClassDay;

public interface ClassDayService {
	
	public void addClassDay(ClassDay classDay);

	public List<ClassDay> getAllClassDays();

	public void deleteClassDay(Integer classDayId);

	public ClassDay getClassDay(int classDayid);

	public ClassDay updateClassDay(ClassDay classDay);
	
	public List<Integer> listClassDaysIds();
}
