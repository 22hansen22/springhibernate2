package com.jwt.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "EXITTICKET")
public class ExitTicket implements Serializable {

	private static final long serialVersionUID = -3465813074586302847L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column
	private String title;
	
	@Column
	private String dateET;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getDateET() {
		return dateET;
	}

	public void setDateET(String dateET) {
		this.dateET = dateET;
	}


	/***-----------------------------------------------------------------------------***/
	
	@OneToMany(mappedBy = "exitTicket",cascade = CascadeType.ALL)
	private Set<UserExitTicket> userExitTickets = new HashSet<UserExitTicket>();
    public Set<UserExitTicket> getUserGroups() {
        return userExitTickets;
    }

    public void setUserGroups(Set<UserExitTicket> groups) {
        this.userExitTickets = groups;
    } 

    public void addUserGroup(UserExitTicket userGroup) {
        this.userExitTickets.add(userGroup);
    }

}