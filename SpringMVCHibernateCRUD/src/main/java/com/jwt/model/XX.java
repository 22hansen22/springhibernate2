package com.jwt.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "XX")
public class XX implements Serializable {

	private static final long serialVersionUID = -3465813074586302847L;
	
	

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column
	private String title;

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
	
	/***-----------------------------------------------------------------------------***/
	
	@OneToMany(mappedBy = "xx")
	private Set<EmployeeXX> employeeXXs = new HashSet<EmployeeXX>();
    public Set<EmployeeXX> getUserGroups() {
        return employeeXXs;
    }

    public void setUserGroups(Set<EmployeeXX> groups) {
        this.employeeXXs = groups;
    } 

    public void addUserGroup(EmployeeXX userGroup) {
        this.employeeXXs.add(userGroup);
    }

}