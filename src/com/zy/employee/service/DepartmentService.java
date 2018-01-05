package com.zy.employee.service;

import java.util.List;

import com.zy.employee.entity.Department;

public interface DepartmentService {

	
	public int insertDepartment(Department department);//新增部门
	
	public int updateDepartment(Department department);//修改部门
	
	public List<Department> getAllDepartment();//查询所有部门信息
	
	public Department getByDepId(int id);//根据部门id查到指定部门
	
	public Department getByDepName(String depName);//根据部门名称查到指定部门
}
