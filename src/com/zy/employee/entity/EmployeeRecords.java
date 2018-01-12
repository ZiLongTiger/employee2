package com.zy.employee.entity;

import java.util.List;

public class EmployeeRecords {

	private String name;
	
	private List<Records>recList;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Records> getRecList() {
		return recList;
	}

	public void setRecList(List<Records> recList) {
		this.recList = recList;
	}

	public EmployeeRecords(String name, List<Records> recList) {
		super();
		this.name = name;
		this.recList = recList;
	}
}
