package com.jwt.service;

import java.util.List;

import com.jwt.model.User;

public interface UserService {
	
	public void addUser(User user);

	public List<User> getAllUsers();

	public void deleteUser(Integer userId);

	public User getUser(int userid);

	public User updateUser(User user);
	
	public boolean findUser(String uname,String upwd);
	
	public User getUserByNameAndPassword(String uname,String upwd);
	
	public List<Integer> listUserIds();
	
	public List<User> getAllStudents();
}
