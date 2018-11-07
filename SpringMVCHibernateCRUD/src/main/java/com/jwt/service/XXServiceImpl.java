package com.jwt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jwt.dao.EmployeeDAO;
import com.jwt.dao.XXDAO;
import com.jwt.model.Employee;
import com.jwt.model.XX;

@Service
@Transactional
public class XXServiceImpl implements XXService {

	@Autowired
	private XXDAO xxDAO;

	@Override
	@Transactional
	public void addXX(XX xx) {
		xxDAO.addXX(xx);
	}

	@Override
	@Transactional
	public List<XX> getAllXXs() {
		return xxDAO.getAllXXs();
	}

	@Override
	@Transactional
	public void deleteXX(Integer xxId) {
		xxDAO.deleteXX(xxId);
	}

	public XX getXX(int empid) {
		return xxDAO.getXX(empid);
	}

	public XX updateXX(XX xx) {
		// TODO Auto-generated method stub
		return xxDAO.updateXX(xx);
	}

	public void setXXDAO(XXDAO xxDAO) {
		this.xxDAO = xxDAO;
	}

}
