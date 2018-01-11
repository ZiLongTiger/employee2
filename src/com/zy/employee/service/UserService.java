package com.zy.employee.service;

import com.zy.employee.entity.User;

public interface UserService {

	public User login(String name,String password);
	
	public int regiseter(User user);
	
	public User getUserById(int id);
	
	public User getUserByName(String name);
	
	public int updateUser(User user);
}
