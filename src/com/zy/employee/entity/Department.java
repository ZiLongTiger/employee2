package com.zy.employee.entity;

public class Department{

	private int id;

	private int depLock;//部门状态 0已删除 1未删除

	private String depName;//部门名称

	private double baseWage;//基本工资

	public Department() {
		super();
	}

	public Department(int depLock, String depName, double baseWage) {
		super();
		this.depLock = depLock;
		this.depName = depName;
		this.baseWage = baseWage;
	}

	public Department(int id, int depLock, String depName, double baseWage) {
		super();
		this.id = id;
		this.depLock = depLock;
		this.depName = depName;
		this.baseWage = baseWage;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDepLock() {
		return depLock;
	}

	public void setDepLock(int depLock) {
		this.depLock = depLock;
	}

	public String getDepName() {
		return depName;
	}

	public void setDepName(String depName) {
		this.depName = depName;
	}

	public double getBaseWage() {
		return baseWage;
	}

	public void setBaseWage(double baseWage) {
		this.baseWage = baseWage;
	}

	@Override
	public String toString() {
		return "Department [id=" + id + ", depLock=" + depLock + ", depName=" + depName + ", baseWage=" + baseWage
				+ "]";
	}
}