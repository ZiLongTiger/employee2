package com.zy.employee.service;

import java.util.List;

import com.zy.employee.entity.Department;

public interface DepartmentService {

	
	public int insertDepartment(Department department);//��������
	
	public int updateDepartment(Department department);//�޸Ĳ���
	
	public List<Department> getAllDepartment();//��ѯ���в�����Ϣ
	
	public Department getByDepId(int id);//���ݲ���id�鵽ָ������
	
	public Department getByDepName(String depName);//���ݲ������Ʋ鵽ָ������
}
