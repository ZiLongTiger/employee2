package com.zy.employee.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zy.employee.dao.DepartmentMapper;
import com.zy.employee.entity.Department;
import com.zy.employee.service.DepartmentService;

@Service("departmentService")
public class DepartmentServiceImpl implements DepartmentService {

	@Autowired
	private DepartmentMapper departmentMapper;
	
	@Override
	public int insertDepartment(Department department) {
		return departmentMapper.insertDepartment(department);
	}

	@Override
	public int updateDepartment(Department department) {
		return departmentMapper.updateDepartment(department);
	}

	@Override
	public List<Department> getAllDepartment() {
		return departmentMapper.getAllDepartment();
	}

	@Override
	public Department getByDepId(int id) {
		return departmentMapper.getByDepId(id);
	}

	@Override
	public Department getByDepName(String depName) {
		return departmentMapper.getByDepName(depName);
	}
}
