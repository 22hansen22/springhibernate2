package com.jwt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jwt.dao.UserDAO;
import com.jwt.dao.ExitTicketDAO;
import com.jwt.model.ExitTicket;
import com.jwt.model.User;

@Service
@Transactional
public class ExitTicketServiceImpl implements ExitTicketService {

	@Autowired
	private ExitTicketDAO exitTicketDAO;

	@Override
	@Transactional
	public void addExitTicket(ExitTicket exitTicket) {
		exitTicketDAO.addExitTicket(exitTicket);
	}

	@Override
	@Transactional
	public List<ExitTicket> getAllExitTickets() {
		return exitTicketDAO.getAllExitTickets();
	}

	@Override
	@Transactional
	public void deleteExitTicket(Integer exitTicketId) {
		exitTicketDAO.deleteExitTicket(exitTicketId);
	}

	public ExitTicket getExitTicket(int empid) {
		return exitTicketDAO.getExitTicket(empid);
	}

	public ExitTicket updateExitTicket(ExitTicket exitTicket) {
		// TODO Auto-generated method stub
		return exitTicketDAO.updateExitTicket(exitTicket);
	}

	public void setExitTicketDAO(ExitTicketDAO exitTicketDAO) {
		this.exitTicketDAO = exitTicketDAO;
	}
	public List<Integer> listExitTicketsIds() {
		return exitTicketDAO.listExitTicketsIds();
	}

}
