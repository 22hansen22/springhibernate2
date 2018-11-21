package com.jwt.dao;

import java.util.List;
import com.jwt.model.ExitTicket;

public interface ExitTicketDAO {

	public void addExitTicket(ExitTicket exitTicket);

	public List<ExitTicket> getAllExitTickets();

	public void deleteExitTicket(Integer exitTicketId);

	public ExitTicket updateExitTicket(ExitTicket exitTicket);

	public ExitTicket getExitTicket(int exitTicketid);
	
	public List<Integer> listExitTicketsIds();
}
