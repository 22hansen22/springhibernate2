package com.jwt.service;

import java.util.List;
import com.jwt.model.ExitTicket;

public interface ExitTicketService {
	
	public void addExitTicket(ExitTicket exitTicket);

	public List<ExitTicket> getAllExitTickets();

	public void deleteExitTicket(Integer exitTicketId);

	public ExitTicket getExitTicket(int exitTicketid);

	public ExitTicket updateExitTicket(ExitTicket exitTicket);
	
	public List<Integer> listExitTicketsIds();
}
