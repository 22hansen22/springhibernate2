package com.jwt.service;

import java.util.List;

import com.jwt.model.Employee;
import com.jwt.model.XX;

public interface XXService {
	
	public void addXX(XX xx);

	public List<XX> getAllXXs();

	public void deleteXX(Integer xxId);

	public XX getXX(int xxid);

	public XX updateXX(XX xx);
}
