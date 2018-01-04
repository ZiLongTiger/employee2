package com.zy.employee.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zy.employee.dao.UserMapper;
import com.zy.employee.entity.User;
import com.zy.employee.service.UserService;
@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;
	
	@Override
	public User login(String name, String password) {
		return userMapper.getUser(name, password);
	}

	@Override
	public int regiseter(User user) {
		return userMapper.insertUser(user);
	}

	@Override
	public User getUserById(int id) {
		return userMapper.getUserById(id);
	}
}
