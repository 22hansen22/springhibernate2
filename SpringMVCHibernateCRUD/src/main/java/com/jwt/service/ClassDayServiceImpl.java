package com.jwt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jwt.dao.UserDAO;
import com.jwt.dao.ClassDayDAO;
import com.jwt.model.ClassDay;
import com.jwt.model.User;

@Service
@Transactional
public class ClassDayServiceImpl implements ClassDayService {

	@Autowired
	private ClassDayDAO classDayDAO;

	@Override
	@Transactional
	public void addClassDay(ClassDay classDay) {
		classDayDAO.addClassDay(classDay);
	}

	@Override
	@Transactional
	public List<ClassDay> getAllClassDays() {
		return classDayDAO.getAllClassDays();
	}

	@Override
	@Transactional
	public void deleteClassDay(Integer classDayId) {
		classDayDAO.deleteClassDay(classDayId);
	}

	public ClassDay getClassDay(int empid) {
		return classDayDAO.getClassDay(empid);
	}

	public ClassDay updateClassDay(ClassDay classDay) {
		// TODO Auto-generated method stub
		return classDayDAO.updateClassDay(classDay);
	}

	public void setClassDayDAO(ClassDayDAO classDayDAO) {
		this.classDayDAO = classDayDAO;
	}
	public List<Integer> listClassDaysIds() {
		return classDayDAO.listClassDaysIds();
	}

}
