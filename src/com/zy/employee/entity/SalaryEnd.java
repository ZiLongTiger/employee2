package com.zy.employee.entity;

import java.util.List;

public class SalaryEnd {

	private Salary salary;
	
	private Employee employee;
	
	private List<Bonus> list;

	public Salary getSalary() {
		return salary;
	}

	public void setSalary(Salary salary) {
		this.salary = salary;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public List<Bonus> getList() {
		return list;
	}

	public void setList(List<Bonus> list) {
		this.list = list;
	}

	public SalaryEnd(Salary salary, Employee employee, List<Bonus> list) {
		super();
		this.salary = salary;
		this.employee = employee;
		this.list = list;
	}

}
