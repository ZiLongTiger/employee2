package com.zy.employee.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zy.employee.entity.Employee;

public interface EmployeeMapper {

	public List<Employee> getAllEmployee();
	
	public Employee getByEmployeeId(int id);
	
	public Employee getByEmployeeByUid(int uid);
	
	public List<Employee> getByEmployeeDeptId(int deptId);
	
	public List<Employee> getByEmployeePosId(int posId);
	
	public List<Employee> getByEmployeePosIdAndDeptId(@Param(value="posId")int posId,@Param(value="deptId")int deptId);
	
	public int insertEmployee(Employee employee);
	
	public int updateEmployee(Employee employee);
	
	public int deleteEmployeeById(int id);
	
}
