package com.jwt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jwt.dao.UserDAO;
import com.jwt.dao.UserExitTicketDAO;
import com.jwt.dao.ExitTicketDAO;
import com.jwt.model.User;
import com.jwt.model.UserExitTicket;
import com.jwt.model.ExitTicket;

@Service
@Transactional
public class UserExitTicketServiceImpl implements UserExitTicketService {

	@Autowired
	private UserExitTicketDAO userExitTicketDAO;

	@Override
	@Transactional
	public void addUserExitTicket(UserExitTicket exitTicket) {
		userExitTicketDAO.addUserExitTicket(exitTicket);
	}

	@Override
	@Transactional
	public List<UserExitTicket> getAllUserExitTickets() {
		return userExitTicketDAO.getAllUserExitTickets();
	}

	@Override
	@Transactional
	public void deleteUserExitTicket(Integer exitTicketId) {
		userExitTicketDAO.deleteUserExitTicket(exitTicketId);
	}

	public UserExitTicket getUserExitTicket(int employeeExitTicketid) {
		return userExitTicketDAO.getUserExitTicket(employeeExitTicketid);
	}

	public UserExitTicket updateUserExitTicket(UserExitTicket exitTicket) {
		// TODO Auto-generated method stub
		return userExitTicketDAO.updateUserExitTicket(exitTicket);
	}

	public void setUserExitTicketDAO(UserExitTicketDAO userExitTicketDAO) {
		this.userExitTicketDAO = userExitTicketDAO;
	}

	public List<UserExitTicket> findExitTicketsByUser(int userId){
		return userExitTicketDAO.findExitTicketsByUser(userId);
	}
	
	public List<UserExitTicket> findUsersByExitTicket(int exitTicketId){
		return userExitTicketDAO.findUsersByExitTicket(exitTicketId);
	}
	
	public int countETByUserID(int userId) {
		return userExitTicketDAO.countETByUserID(userId);
	}
	
	public int countUsersByETID(int exitTicketId) {
		return userExitTicketDAO.countUsersByETID(exitTicketId);
	}
	
	public List<Boolean> getWrittenYN(List<ExitTicket> etList, int userId){
		return userExitTicketDAO.getWrittenYN(etList,userId);
	}
	
	public UserExitTicket getUserExitTicketByUserAndET(int etId, int userId) {
		return userExitTicketDAO.getUserExitTicketByUserAndET(etId,userId);
	}
}
