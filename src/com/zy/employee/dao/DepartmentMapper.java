package com.zy.employee.dao;

import java.util.List;

import com.zy.employee.entity.Department;

public interface DepartmentMapper {

	
	public int insertDepartment(Department department);//��������
	
	public int updateDepartment(Department department);//�޸Ĳ���
	
	public int deleteDepartment(int id);//ɾ������
	
	public List<Department> getAllDepartment();//��ѯ���в�����Ϣ
	
	public Department getByDepId(int id);//���ݲ���id�鵽ָ������
	
	public Department getByDepName(String depName);//���ݲ������Ʋ鵽ָ������
}
