package com.zy.employee.service;

import java.util.List;

import com.zy.employee.entity.Employee;

public interface EmployeeService {

	public List<Employee> getAllEmployee();
	
	public Employee getByEmployeeId(int id);
	
	public Employee getByEmployeeDeptId(int deptId);
	
	public Employee getByEmployeePosId(int posId);
	
	public int insertEmployee(Employee employee);
	
	public int updateEmployee(Employee employee);
	
	public int deleteEmployeeById(int id);
	
}
