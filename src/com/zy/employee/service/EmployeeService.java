package com.zy.employee.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zy.employee.entity.Employee;

public interface EmployeeService {

	public List<Employee> getAllEmployee();
	
	public Employee getByEmployeeByUid(int uid);
	
	public Employee getByEmployeeId(int id);
	
	public List<Employee> getByEmployeeDeptId(int deptId);
	
	public List<Employee> getByEmployeePosId(int posId);
	
	public List<Employee> getByEmployeePosIdAndDeptId(@Param(value="deptId")int deptId,@Param(value="posId")int posId);
	
	public int insertEmployee(Employee employee);
	
	public int updateEmployee(Employee employee);
	
	public int deleteEmployeeById(int id);
	
}
