package com.zy.employee.dao;

import java.util.List;

import com.zy.employee.entity.Department;

public interface DepartmentMapper {

	
	public int insertDepartment(Department department);
	
	public int updateDepartment(Department department);
	
	public List<Department> getAllDepartment();
	
	public Department getByDepId(int id);
}
