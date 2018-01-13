package com.zy.employee.entity;

public class CheckBonus {

	private Employee employee;
	
	private Bonus bonus;

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Bonus getBonus() {
		return bonus;
	}

	public void setBonus(Bonus bonus) {
		this.bonus = bonus;
	}

	public CheckBonus(Employee employee, Bonus bonus) {
		super();
		this.employee = employee;
		this.bonus = bonus;
	}

}
