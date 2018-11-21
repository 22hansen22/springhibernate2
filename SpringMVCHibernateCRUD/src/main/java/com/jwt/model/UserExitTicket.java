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
@Table(name = "USEREXITTICKET")
public class UserExitTicket implements Serializable {

	private static final long serialVersionUID = -3465813074586302847L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column
	private String answer;
	
	@Column
	private String dateAnswer;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@ManyToOne
    @JoinColumn(name = "userId")  
    private User user;
	
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne
    @JoinColumn(name = "ticketId")
    private ExitTicket exitTicket;
    
    public ExitTicket getExitTicket() {
        return exitTicket;
    }

    public void setExitTicket(ExitTicket exitTicket) {
        this.exitTicket = exitTicket;
    }

	
	/***-----------------------------------------------------------------------------***/

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getDateAnswer() {
		return dateAnswer;
	}

	public void setDateAnswer(String dateAnswer) {
		this.dateAnswer = dateAnswer;
	}
	
	

}