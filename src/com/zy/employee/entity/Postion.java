package com.zy.employee.entity;

public class Postion{

	private int id;
	
	private String posName;
	
	private Department department;

	public Postion(int id, String posName, Department department) {
		super();
		this.id = id;
		this.posName = posName;
		this.department = department;
	}

	public Postion() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPosName() {
		return posName;
	}

	public void setPosName(String posName) {
		this.posName = posName;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	@Override
	public String toString() {
		return "Postion [id=" + id + ", posName=" + posName + ", department=" + department + "]";
	}
}