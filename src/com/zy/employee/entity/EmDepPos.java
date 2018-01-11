package com.zy.employee.entity;

import java.util.List;

//员工调动条件类
public class EmDepPos {
	
	private Employee employee;

	private List<Department> depList;
	
	private List<Postion> posList;

	public List<Department> getDepList() {
		return depList;
	}

	public void setDepList(List<Department> depList) {
		this.depList = depList;
	}

	public List<Postion> getPosList() {
		return posList;
	}

	public void setPosList(List<Postion> posList) {
		this.posList = posList;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public EmDepPos(Employee employee, List<Department> depList, List<Postion> posList) {
		super();
		this.employee = employee;
		this.depList = depList;
		this.posList = posList;
	}
}
