package com.zy.employee.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zy.employee.dao.EmployeeMapper;
import com.zy.employee.entity.Employee;
import com.zy.employee.service.EmployeeService;

@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeMapper employeeMapper;
	
	@Override
	public List<Employee> getAllEmployee() {
		return employeeMapper.getAllEmployee();
	}

	@Override
	public Employee getByEmployeeId(int id) {
		return employeeMapper.getByEmployeeId(id);
	}

	@Override
	public List<Employee> getByEmployeeDeptId(int deptId) {
		return employeeMapper.getByEmployeeDeptId(deptId);
	}

	@Override
	public List<Employee> getByEmployeePosId(int posId) {
		return employeeMapper.getByEmployeePosId(posId);
	}

	@Override
	public int insertEmployee(Employee employee) {
		return employeeMapper.insertEmployee(employee);
	}

	@Override
	public int updateEmployee(Employee employee) {
		return employeeMapper.updateEmployee(employee);
	}

	@Override
	public int deleteEmployeeById(int id) {
		return employeeMapper.deleteEmployeeById(id);
	}

	@Override
	public List<Employee> getByEmployeePosIdAndDeptId(int deptId, int posId) {
		return employeeMapper.getByEmployeePosIdAndDeptId(deptId, posId);
	}

	@Override
	public Employee getByEmployeeByUid(int uid) {
		return employeeMapper.getByEmployeeByUid(uid);
	}

}
