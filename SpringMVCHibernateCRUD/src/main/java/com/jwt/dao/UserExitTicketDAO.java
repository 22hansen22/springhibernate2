package com.jwt.dao;

import java.util.List;
import com.jwt.model.User;
import com.jwt.model.UserExitTicket;
import com.jwt.model.ExitTicket;

public interface UserExitTicketDAO {

	public void addUserExitTicket(UserExitTicket exitTicket);

	public List<UserExitTicket> getAllUserExitTickets();

	public void deleteUserExitTicket(Integer exitTicketId);

	public UserExitTicket updateUserExitTicket(UserExitTicket exitTicket);

	public UserExitTicket getUserExitTicket(int exitTicketid);
	
	public List<UserExitTicket> findExitTicketsByUser(int userId);
	
	public List<UserExitTicket> findUsersByExitTicket(int exitTicketId);
	
	public int countETByUserID(int userId);
	
	public int countUsersByETID(int exitTicketId);
	
	public List<Boolean> getWrittenYN(List<ExitTicket> etList, int userId);
	
	public UserExitTicket getUserExitTicketByUserAndET(int etId, int userId);
}
