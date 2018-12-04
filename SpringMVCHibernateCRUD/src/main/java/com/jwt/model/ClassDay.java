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
@Table(name = "CLASSDAY")
public class ClassDay implements Serializable {

	private static final long serialVersionUID = -3465813074586302847L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column
	private String password;
	
	@Column
	private String question;
	
	@Column
	private String dateClass;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}
	
	public String getDateClass() {
		return dateClass;
	}

	public void setDateClass(String dateClass) {
		this.dateClass = dateClass;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	/***-----------------------------------------------------------------------------***/
	
	@OneToMany(mappedBy = "classDay",cascade = CascadeType.ALL)
	private Set<UserClassDay> userClassDay = new HashSet<UserClassDay>();
    
	/*
	public Set<UserClassDay> getUserGroups() {
        return userClassDay;
    }

    public void setUserGroups(Set<UserClassDay> userClassDay) {
        this.userClassDay = userClassDay;
    } 

    public void addUserGroup(UserClassDay userGroup) {
        this.userClassDay.add(userGroup);
    }*/

}