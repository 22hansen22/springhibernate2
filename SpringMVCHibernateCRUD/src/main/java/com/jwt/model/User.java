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
@Table(name = "USER")
public class User implements Serializable {

	private static final long serialVersionUID = -3465813074586302847L;
	

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column
	private String name;

	@Column
	private String password;

	@Column
	private String type;

	@Column
	private String realname;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}


/***---------------------- for EXIT TICKET   ---------------------------------------***/
	
	@OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
	private Set<UserExitTicket> userExitTickets = new HashSet<UserExitTicket>();
	
   /* public Set<UserExitTicket> getUserGroups() {
        return userExitTickets;
    }

    public void setUserGroups(Set<UserExitTicket> groups) {
        this.userExitTickets = groups;
    } 

    public void addUserGroup(UserExitTicket userGroup) {
        this.userExitTickets.add(userGroup);
    }*/

/***------------------------- for CLASS DAY --------------------------***/
	
	@OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
	private Set<UserClassDay> userClassDay = new HashSet<UserClassDay>();
	
   /* public Set<UserClassDay> getUserGroups() {
        return userExitTickets;
    }

    public void setUserGroups(Set<UserClassDay> groups) {
        this.userExitTickets = groups;
    } 

    public void addUserGroup(UserClassDay userGroup) {
        this.userExitTickets.add(userGroup);
    }*/

}