package com.jwt.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "EMP_TBL_XX")
public class EmployeeXX implements Serializable {

	private static final long serialVersionUID = -3465813074586302847L;
    private Employee employee;
    private XX xx;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column
	private String answer;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "emp_tbl_id")  
    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "xx_id")
    public XX getXX() {
        return xx;
    }

    public void setXX(XX xx) {
        this.xx = xx;
    }

	
	/***-----------------------------------------------------------------------------***/

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

}