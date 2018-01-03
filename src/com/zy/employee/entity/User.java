package com.zy.employee.entity;

public class User{
	
	private int id;
	
	private String name;
	
	private String password;
	
	private int role;//0普通用户  1管理员
	
	private int userLock;//0 删除  1未删除	

	public User(String name, String password, int role, int userLock) {
		super();
		this.name = name;
		this.password = password;
		this.role = role;
		this.userLock = userLock;
	}

	public User() {
		super();
	}

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

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	public int getUserLock() {
		return userLock;
	}

	public void setUserLock(int userLock) {
		this.userLock = userLock;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", password=" + password + ", role=" + role + ", userLock="
				+ userLock + "]";
	}
}