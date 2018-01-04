package com.zy.employee.service;

import java.util.List;

import com.zy.employee.entity.Department;

public interface DepartmentService {

	
	public int insertDepartment(Department department);
	
	public int updateDepartment(Department department);
	
	public List<Department> getAllDepartment();
	
	public Department getByDepId(int id);
}
